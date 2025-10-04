package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utils{
    private static final String url = "jdbc:postgresql://localhost:5433/test";
    private static final String user = "postgres";
    private static final String password = "postgres"; // Use the password you set during installation

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }
}