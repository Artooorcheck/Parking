package SQLQuery;

import Services.AuthorizationService;
import Services.CarService;
import jakarta.xml.bind.ValidationException;
import org.junit.jupiter.api.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ParkingTest extends BaseTest {

    private CarService service;

    private static final String LOGIN = "ghjiiidj";

    @BeforeAll
    public void signUp() {
        service = new CarService(getProperties());
        var password = "ghfhqwe";
        var cardNumber = "9895332298471234";
        var name = "Федор";
        assertDoesNotThrow(() -> {
            new AuthorizationService(getProperties()).createUser(LOGIN, password, cardNumber, name);
        });
    }


    @Test
    @Order(1)
    public void setCarInParkValid() {
        String placeId = "13";
        String carId = "А111АА11";
        assertDoesNotThrow(() -> {
            service.leaveCar(placeId, LOGIN, carId);
        });
    }

    @Test
    @Order(2)
    public void getCarInParkValid() {
        try {
            assertNotEquals(service.getUserCars(LOGIN).stream()
                    .filter(a -> a.getCarId().equals("А111АА11") && a.getPlaceId() == 13).count(), 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    public void removeCarFromParkInvalidLogin() {
        String placeId = "13";
        String login = "LOGIN";
        try {
            service.removeCar(placeId, login);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            assertNotEquals(service.getUserCars(LOGIN).stream()
                    .filter(a -> a.getCarId().equals("А111АА11") && a.getPlaceId() == 13).count(), 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    public void getCarAfterRemoveValid() {
        String placeId = "13";
        try {
            service.removeCar(placeId, LOGIN);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            assertEquals(service.getUserCars(LOGIN).stream()
                    .filter(a -> a.getCarId().equals("А111АА11") && a.getPlaceId() == 13).count(), 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setCarInParkInvalidCarId() {
        String placeId = "13";
        String carId = "А111А11";
        assertThrows(ValidationException.class, () -> {
            service.leaveCar(placeId, LOGIN, carId);
        });
    }


    @Test
    public void setCarInParkInvalidPlace() {
        String placeId = "113";
        String carId = "А111АА11";
        assertThrows(ValidationException.class, () -> {
            service.leaveCar(placeId, LOGIN, carId);
        });
    }


    @Test
    public void setCarInParkInvalidLogin() {
        String placeId = "13";
        String carId = "А111АА11";
        String login = "LOGIN";
        try {
            service.leaveCar(placeId, login, carId);
        } catch (ValidationException | SQLException e) {
            e.printStackTrace();
            return;
        }

        try {
            assertEquals(service.getUserCars(login).stream()
                    .filter(a -> a.getCarId().equals("А111АА11") && a.getPlaceId() == 13).count(), 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @AfterAll
    public void deleteAccount() {
        var password = "ghfhqwe";
        var cardNumber = "9895332298471234";
        var name = "Федор";
        try {
            new AuthorizationService(getProperties()).deleteUser(LOGIN);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
