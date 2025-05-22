package ma.banque.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/bankati?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // ➕ charge manuellement le driver
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver MySQL introuvable", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
