package SQLQuery;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertTrue;

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
        var query = new SignUpQuery();
        query.setParams(params);
        boolean result = false;
        try {
            query.execute();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertTrue(result);
    }

    @Test
    @Order(2)
    public void signIn() {
        Map<String, Object> params = new TreeMap<>();
        params.put("Login", "ghjiiidj");
        params.put("Password", "ghfhqwe");
        var query = new LogInQuery();
        query.setParams(params);
        boolean result = false;
        try {
            query.execute();
            result = query.getResult();
        } catch (SQLException e) {}
        assertTrue(result);
    }

    @Test
    @Order(3)
    public void invalidAccountLogin() {
        Map<String, Object> params = new TreeMap<>();
        params.put("Login", "ghjiiidj");
        params.put("Password", "ghfhqwa");
        var query = new LogInQuery();
        query.setParams(params);
        boolean result = true;
        try {
            query.execute();
            result = query.getResult();
        } catch (SQLException e) {}
        assertTrue(!result);
    }

    @Test
    @Order(4)
    public void deleteAccount() {
        Map<String, Object> params = new TreeMap<>();
        params.put("Login", "ghjiiidj");
        var query = new DeleteUserQuery();
        query.setParams(params);
        boolean result = false;
        try {
            query.execute();
            result = true;
        } catch (SQLException e) {}
        assertTrue(result);
    }

    @Test
    @Order(5)
    public void deletedAccountLogin() {
        Map<String, Object> params = new TreeMap<>();
        params.put("Login", "ghjiiidj");
        params.put("Password", "ghfhqwa");
        var query = new LogInQuery();
        query.setParams(params);
        boolean result = true;
        try {
            query.execute();
            result = query.getResult();
        } catch (SQLException e) {}
        assertTrue(!result);
    }
}