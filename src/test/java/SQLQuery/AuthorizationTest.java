package SQLQuery;

import Services.AuthorizationService;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthorizationTest extends BaseTest {

    private AuthorizationService service;

    public AuthorizationTest() {
        service = new AuthorizationService(getProperties());
    }

    @Test
    @Order(1)
    public void signUp() {
        var login = "ghjiiidj";
        var password = "ghfhqwe";
        var cardNumber = "9895332298471234";
        var name = "Федор";
        assertDoesNotThrow(() -> {
            service.createUser(login, password, cardNumber, name);
        });
    }

    @Test
    @Order(2)
    public void signIn() {
        var login = "ghjiiidj";
        var password = "ghfhqwe";
        try {
            assertTrue(service.loginIsExist(login, password));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    public void invalidAccountLogin() {
        var login = "ghjidj";
        var password = "ghfhqwe";
        try {
            Assertions.assertFalse(service.loginIsExist(login, password));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    public void deleteAccount() {
        var login = "ghjiiidj";
        var password = "ghfhqwe";
        try {
            service.deleteUser(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            assertFalse(service.loginIsExist(login, password));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}