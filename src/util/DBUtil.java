package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    // PostgreSQL bağlantı bilgileri
    private static final String URL = "jdbc:postgresql://localhost:5432/carrental";
    private static final String USER = "postgres";   // kendi kullanıcı adını yaz
    private static final String PASSWORD = "1234";   // kendi PostgreSQL şifreni yaz

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
