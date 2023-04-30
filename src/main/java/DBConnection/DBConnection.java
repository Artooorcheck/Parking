package DBConnection;


import Props.PropertyLinker;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Connection connection;

    protected Connection getConnection(String url, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            var connectionString = PropertyLinker.getProperty("connectionString");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        try {
            connection = DriverManager
                    .getConnection(url, user, password);

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
