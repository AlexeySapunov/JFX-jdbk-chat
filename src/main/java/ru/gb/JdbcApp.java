package ru.gb;

import java.sql.*;

public class JdbcApp {
    private Connection connection;

    public static void main(String[] args) {
        final JdbcApp jdbcApp = new JdbcApp();
        jdbcApp.run();
    }

    private void run() {
        try {
            connect();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:javadb.db");
    }

    private void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
