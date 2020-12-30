package ru.geekbrains.lesson2.database;

import java.sql.*;

public class SQLService {
    private static Connection connection;
    private static PreparedStatement psGetNick;
    private static PreparedStatement psReg;
    private static PreparedStatement psChangeNick;

    private static final String urlConnectionDatabase = "jdbc:sqlite:C://Users//Zurbaevi Nika//Documents//GitHub//Java-Professional-level//Java-Professional-level//src//ru//geekbrains//lesson2//src//main//java//ru//geekbrains//lesson2/database//firstDatabase.db";

    public static boolean connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(urlConnectionDatabase);
            prepareAllStatements();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void prepareAllStatements() throws SQLException {
        psGetNick = connection.prepareStatement("SELECT name FROM users WHERE name = ? AND password = ?;");
        psReg = connection.prepareStatement("INSERT INTO users (name, password) VALUES (?, ?);");
        psChangeNick = connection.prepareStatement("UPDATE users SET name = ? WHERE name = ?;");
    }

    public static String getNicknameByLoginAndPassword(String login, String password) {
        String nick = null;
        try {
            psGetNick.setString(1, login);
            psGetNick.setString(2, password);
            ResultSet rs = psGetNick.executeQuery();
            if (rs.next()) {
                nick = rs.getString(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nick;
    }

    public static boolean registration(String nickname, String password) {
        try {
            psReg.setString(1, nickname);
            psReg.setString(2, password);
            psReg.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean changeNick(String oldNick, String newNick) {
        try {
            psChangeNick.setString(1, newNick);
            psChangeNick.setString(2, oldNick);
            psChangeNick.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void disconnect() {
        try {
            psReg.close();
            psGetNick.close();
            psChangeNick.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
