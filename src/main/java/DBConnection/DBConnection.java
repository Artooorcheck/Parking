package DBConnection;

//STEP 1. Import required packages

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private final String DB_URL = "jdbc:postgresql://localhost/Parking";
    private final String USER = "postgres";
    private final String PASS = "admin";
    private Connection connection;

    protected Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
        System.out.println("Connected");
        return connection;
    }

    protected void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
