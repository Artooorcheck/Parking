package SQLQuery;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthorizationTest {


    @Test
    @Order(1)
    public void signUp() {
        Map<String, Object> params = new TreeMap<>();
        params.put("Name", "Федор");
        params.put("Card_number", "9895332298471234");
        params.put("Login", "ghjiiidj");
        params.put("Password", "ghfhqwe");
        assertDoesNotThrow(() -> {
            var query = new SignUpQuery(getProperties());
            query.setParams(params);
            query.execute();
        });
    }

    @Test
    @Order(2)
    public void signIn() {
        Map<String, Object> params = new TreeMap<>();
        params.put("Login", "ghjiiidj");
        params.put("Password", "ghfhqwe");
        try {
            var query = new LogInQuery(getProperties());
            query.setParams(params);
            query.execute();
            assertTrue(query.getResult());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    public void invalidAccountLogin() {
        Map<String, Object> params = new TreeMap<>();
        params.put("Login", "ghjiiidj");
        params.put("Password", "ghfhqwa");
        try {
            var query = new LogInQuery(getProperties());
            query.setParams(params);
            query.execute();
            assertFalse(query.getResult());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    public void deleteAccount() {
        Map<String, Object> params = new TreeMap<>();
        params.put("Login", "ghjiiidj");
        params.put("Password", "ghfhqwe");
        try {
            var query = new DeleteUserQuery(getProperties());
            query.setParams(params);
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean result = false;
        try {
            var getQuery = new LogInQuery(getProperties());
            getQuery.setParams(params);
            getQuery.execute();
            assertFalse(getQuery.getResult());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(5)
    public void deletedAccountLogin() {
        Map<String, Object> params = new TreeMap<>();
        params.put("Login", "ghjiiidj");
        params.put("Password", "ghfhqwe");
        boolean result = false;
        try {
            var query = new LogInQuery(getProperties());
            query.setParams(params);
            query.execute();
            assertFalse(query.getResult());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("./src/test/java/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}