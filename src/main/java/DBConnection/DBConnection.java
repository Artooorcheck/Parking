package DBConnection;

//STEP 1. Import required packages

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {

    private static final String DB_URL = "jdbc:postgresql://localhost/Parking";
    private static final String USER = "postgres";
    private static final String PASS = "admin";

    private static Connection connection;

    public static Connection getConnection() {

        if (connection != null) {
            return connection;
        }

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
        return connection;
    }
}
