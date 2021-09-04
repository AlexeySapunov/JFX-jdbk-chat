package ru.gb.server;

public interface AuthService {
    String getNicknameByLoginAndPassword(String login, String password);
}
