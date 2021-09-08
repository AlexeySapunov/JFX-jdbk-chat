package ru.gb.server;

import org.sqlite.JDBC;

import java.sql.*;

public class SimpleAuthService implements AuthService {

    private static final String INSERT_NEW_USER = "INSERT INTO users(login, password, nick) VALUES(?, ?, ?);";
    private static final String LOGIN = "SELECT nick FROM users WHERE login = ? AND password = ?;";
    private static final String OPEN_SESSION = "UPDATE users set session = 'online' WHERE id = ?;";
    private static final String URL = "jdbc:sqlite:chatdb.db";

    private int id;
    private String login;
    private String password;
    private String nick;
    private Connection connection;
    private Statement statement;

    public void run() {
        try {
            connectBase();
            users();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectBase();
        }

    }

    public SimpleAuthService() throws SQLException {
        if (connection != null) {
            try (final PreparedStatement ps = connection.prepareStatement(INSERT_NEW_USER)) {
                ps.setString(1, login);
                ps.setString(2, password);
                ps.setString(3, nick);
                ps.executeUpdate();
            }
        }
    }

    @Override
    public String getNicknameByLoginAndPassword(final String login, final String password) throws SQLException {
        if (connection != null) {
            try (final PreparedStatement ps = connection.prepareStatement(LOGIN)) {
                ps.setString(1, nick);
                ps.setString(2, login);
                ps.setString(3, password);
                final ResultSet resultSet = ps.executeQuery();
                setValues(resultSet);
                ps.executeUpdate();
            }
            try (final PreparedStatement ps1 = connection.prepareStatement(OPEN_SESSION)) {
                ps1.setInt(1, id);
                ps1.executeUpdate();
            }
        }
        return null;
    }

    public void setValues(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                id = resultSet.getInt("id");
                login = resultSet.getString("login");
                password = resultSet.getString("password");
                nick = resultSet.getString("nick");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void connectBase() throws SQLException {
        Driver driver = new JDBC();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection(URL);
        statement = connection.createStatement();
    }

    private void users() throws SQLException {
        statement.executeUpdate("create table if not exists users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nick TEXT," +
                "login TEXT," +
                "password TEXT" +
                ")");
    }

    private void disconnectBase() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
