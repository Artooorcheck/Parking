package SQLQuery;

import jakarta.xml.bind.ValidationException;
import org.junit.jupiter.api.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ParkingTest {

    private static final String LOGIN = "ghjiiidj";

    @BeforeAll
    public void signUp() {
        Map<String, Object> params = new TreeMap<>();
        params.put("Name", "Федор");
        params.put("Card_number", "9895332298471234");
        params.put("Login", LOGIN);
        params.put("Password", "ghfhqwe");
        try {
            var query = new SignUpQuery(getProperties());
            query.setParams(params);
            query.execute();
        } catch (SQLException | ValidationException e) {
            e.printStackTrace();
        }
    }


    @Test
    @Order(1)
    public void setCarInParkValid() {
        var params = new HashMap<String, Object>();
        params.put("Place_id", "13");
        params.put("Login", LOGIN);
        params.put("Car_id", "А111АА11");
        assertDoesNotThrow(() -> {
            var query = new LeaveCarQuery(getProperties());
            query.setParams(params);
            query.execute();
        });
    }

    @Test
    @Order(2)
    public void getCarInParkValid() {

        var params = new HashMap<String, Object>();
        params.put("Place_id", "13");
        params.put("Login", LOGIN);
        params.put("Car_id", "А111АА11");

        try {
            var query = new UserPlacesQuery(getProperties());
            query.setParams(params);
            query.execute();
            assertNotEquals(query.getResult().stream()
                    .filter(a -> a.getCarId().equals("А111АА11") && a.getPlaceId() == 13).count(), 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    public void removeCarFromParkInvalidLogin() {
        var params = new HashMap<String, Object>();
        params.put("Place_id", 13);
        params.put("Login", "LOGIN");
        params.put("Car_id", "А111АА11");
        try {
            var query = new RemoveCarQuery(getProperties());
            query.setParams(params);
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            var getQuery = new UserPlacesQuery(getProperties());
            getQuery.setParams(params);
            getQuery.execute();
            assertEquals(getQuery.getResult().stream()
                    .filter(a -> a.getCarId().equals("А111АА11") && a.getPlaceId() == 13).count(), 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    public void getCarAfterRemoveValid() {
        var params = new HashMap<String, Object>();
        params.put("Place_id", 13);
        params.put("Login", LOGIN);
        params.put("Car_id", "А111АА11");
        try {
            var query = new RemoveCarQuery(getProperties());
            query.setParams(params);
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            var getQuery = new UserPlacesQuery(getProperties());
            getQuery.setParams(params);
            getQuery.execute();
            assertEquals(getQuery.getResult().stream()
                    .filter(a -> a.getCarId().equals("А111АА11") && a.getPlaceId() == 13).count(), 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setCarInParkInvalidCarId() {
        var params = new HashMap<String, Object>();
        params.put("Place_id", "13");
        params.put("Login", LOGIN);
        params.put("Car_id", "А111А11");
        assertThrows(ValidationException.class, () -> {
            var query = new LeaveCarQuery(getProperties());
            query.setParams(params);
            query.execute();
        });
    }


    @Test
    public void setCarInParkInvalidPlace() {
        var params = new HashMap<String, Object>();
        params.put("Place_id", "113");
        params.put("Login", LOGIN);
        params.put("Car_id", "А111АА11");
        assertThrows(ValidationException.class,() -> {
            var query = new LeaveCarQuery(getProperties());
            query.setParams(params);
            query.execute();
        });
    }


    @Test
    public void setCarInParkInvalidLogin() {
        var params = new HashMap<String, Object>();
        params.put("Place_id", "13");
        params.put("Login", "LOGIN");
        params.put("Car_id", "А111АА11");
        try {
            var query = new LeaveCarQuery(getProperties());
            query.setParams(params);
            query.execute();
        } catch (ValidationException | SQLException e) {
            e.printStackTrace();
        }

        try {
            var getQuery = new UserPlacesQuery(getProperties());
            getQuery.setParams(params);
            getQuery.execute();
            assertEquals(getQuery.getResult().stream()
                    .filter(a -> a.getCarId().equals("А111АА11") && a.getPlaceId() == 13).count(), 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @AfterAll
    public void deleteAccount() {
        Map<String, Object> params = new TreeMap<>();
        params.put("Login", "ghjiiidj");
        try {
            var query = new DeleteUserQuery(getProperties());
            query.setParams(params);
            query.execute();
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
