package ru.gb.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

    private final AuthService authService;
    private final List<ClientHandler> clients;

    public static final Logger logger = LogManager.getLogger(ChatServer.class);

    public ChatServer() throws SQLException {
        clients = new ArrayList<>();
        authService = new SimpleAuthService();

        try(ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("SERVER: Server start...");
            logger.info("SERVER: Server start...");
            SimpleAuthService.connectBase();
            //noinspection InfiniteLoopStatement
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            SimpleAuthService.disconnectBase();
        }
    }

    public void broadcastClientList() {
        StringBuilder sb = new StringBuilder("/clients ");
        for (ClientHandler client : clients) {
            sb.append(client.getName()).append(" ");
        }
        broadcast(sb.toString());
    }

    public void broadcast(String msg) {
        for (ClientHandler client : clients) {
            client.sendMessage(msg);
        }
    }

    public void subscribe(ClientHandler clientHandler) {
        System.out.println("SERVER: Client " + clientHandler.getName() + " login...");
        clients.add(clientHandler);
        broadcastClientList();
    }

    public void unsubscribe(ClientHandler clientHandler) {
        System.out.println("SERVER: Client " + clientHandler.getName() + " logout...");
        clients.remove(clientHandler);
        broadcastClientList();
    }

    public void sendMsgToClient(ClientHandler from, String nickTo, String msg) {
        for (ClientHandler client : clients) {
            if (client.getName().equals(nickTo)) {
                client.sendMessage("от " + from.getName() + ": " + msg);
                from.sendMessage("клиенту " + nickTo + ": " + msg);
                return;
            }
        }
        from.sendMessage("Участника с ником " + nickTo + " нет в чат-комнате");
    }

    public AuthService getAuthService() {
        return authService;
    }

    public boolean isNicknameBusy(String nickname) {
        for (ClientHandler client : clients) {
            if (client.getName().equals(nickname)) {
                return true;
            }
        }
        return false;
    }
}
