package ru.gb.client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import ru.gb.server.SimpleAuthService;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private String nick;
    private final File history = new File("C:\\Java\\gb-chatJava2Lsn7\\history.txt");

    @FXML
    public TextField regLogin;
    @FXML
    public PasswordField regPassword;
    @FXML
    public TextField regNick;
    @FXML
    public HBox regPanel;
    @FXML
    public HBox clientPanel;
    @FXML
    public HBox msgPanel;
    @FXML
    public Button btnSend;
    @FXML
    public TextField textField;
    @FXML
    private TextArea textArea;
    @FXML
    private ListView<String> clientList;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField loginField;
    @FXML
    private HBox authPanel;

    private void connect() {
        try {
            socket = new Socket("localhost", 8189);
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            setAuth(false);

            new Thread(() -> {
                try {
                    socket.setSoTimeout(120000);
                    while (true) { // Ждем сообщения об успешной авторизации ("/authOk")
                        final String msgAuth = in.readUTF();
                        System.out.println("CLIENT: Received message: " + msgAuth);
                        if (msgAuth.startsWith("/authOk")) {
                            setAuth(true);
                            nick = msgAuth.split("\\s")[1];
                            textArea.appendText("Успешная авторизация под ником " + nick + "\n");
                            break;
                        }
                        //noinspection ConstantConditions
                        if (setAuth(false)) {
                            socket.setSoTimeout(0);
                            return;
                        }
                        textArea.appendText(msgAuth + "\n");
                    }
                    while (true) {
                        String msgFromServer = in.readUTF();
                        System.out.println("CLIENT: Received message: " + msgFromServer);
                        if (msgFromServer.startsWith(nick)) {
                            msgFromServer = "[You] " + msgFromServer;
                        }
                        if ("/end".equalsIgnoreCase(msgFromServer)) {
                            break;
                        }
                        if (msgFromServer.startsWith("/clients")) {
                            final List<String> clients = new ArrayList<>(Arrays.asList(msgFromServer.split("\\s")));
                            clients.remove(0);
                            clientList.getItems().clear();
                            clientList.getItems().addAll(clients);
                            continue;
                        }
                        textArea.appendText(msgFromServer + "\n");
                        loadHistory();
                        saveHistory();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                } finally {
                    try {
                        setAuth(false);
                        socket.close();
                        nick = "";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private boolean setAuth(boolean isAuthSuccess) {
        authPanel.setVisible(!isAuthSuccess);
        authPanel.setManaged(!isAuthSuccess);

        regPanel.setVisible(!isAuthSuccess);
        regPanel.setManaged(!isAuthSuccess);

        msgPanel.setVisible(isAuthSuccess);
        msgPanel.setManaged(isAuthSuccess);

        clientPanel.setVisible(isAuthSuccess);
        clientPanel.setManaged(isAuthSuccess);
        return isAuthSuccess;
    }

    public void sendAuth() {
        if (socket == null || socket.isClosed()) {
            connect();
        }

        try {
            System.out.println("CLIENT: Send auth message");
            out.writeUTF("/auth " + loginField.getText() + " " + passwordField.getText());
            loginField.clear();
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg() {
        try {
            final String msg = textField.getText();
            System.out.println("CLIENT: Send message to server: " + msg);
            out.writeUTF(msg);
            textField.clear();
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connect();
    }

    public void selectClient(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            final String msg = textField.getText();
            String nickname = clientList.getSelectionModel().getSelectedItem();
            textField.setText("/w " + nickname + " " + msg);
            textField.requestFocus();
            textField.selectEnd();
        }
    }

    private void saveHistory() {
        try (final PrintWriter fileWriter = new PrintWriter(new FileWriter(history, false));
             final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(textArea.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadHistory() {
        int msgNum = 100;
        if (!history.exists()) {
            try {
                history.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(history))) {
            writer.write(textArea.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
        final List<String> historyList = new ArrayList<>();
        try (final FileInputStream in = new FileInputStream(history);
             final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in))) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                historyList.add(str);
            }
//            if (historyList.size() > msgNum) {
//                for (int i = historyList.size() - msgNum; i <= (historyList.size() - 1); i++) {
//                    textArea.appendText(historyList.get(i) + "\n");
//                }
//            } else {
//                for (int i = 0; i < msgNum; i++) {
//                    System.out.println(historyList.get(i)); тут вылетает IndexOutOfBoundException, не успел разобраться
//                }
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registration() throws SQLException {
        if(socket == null || socket.isClosed()) {
            connect();
        }
        SimpleAuthService.insertNewUsers(regLogin.getText(), regPassword.getText(), regNick.getText());
        System.out.println("Поздравляем с успешной регистрацией.\nВойдите через форму авторизации. ");
        regLogin.clear();
        regPassword.clear();
        regNick.clear();
    }
}
