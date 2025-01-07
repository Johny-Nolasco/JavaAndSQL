package src;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
    private String url;
    private String user;
    private String password;
    private Connection connection;

    public DBManager(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    // Method to connect to the database
    public void connectToDB() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database.");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
    }

    // Method to disconnect from the database
    public void disconnectFromDB() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from the database.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to disconnect from the database: " + e.getMessage());
        }
    }

    // Generic method to execute a query (SELECT)
    public ResultSet executeSelect(String query) {
        try {
            if (connection == null || connection.isClosed()) {
                connectToDB();
            }
            PreparedStatement pstmt = connection.prepareStatement(query);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Failed to execute query: " + e.getMessage());
        }
        return null;
    }

    // Generic method to execute updates (INSERT, UPDATE, DELETE)
    public int executeUpdate(String query) {
        try {
            if (connection == null || connection.isClosed()) {
                connectToDB();
            }
            PreparedStatement pstmt = connection.prepareStatement(query);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to execute update: " + e.getMessage());
        }
        return 0;
    }
}
