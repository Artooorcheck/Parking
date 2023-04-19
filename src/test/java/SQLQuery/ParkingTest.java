package SQLQuery;

import org.junit.jupiter.api.*;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        var query = new SignUpQuery();
        query.setParams(params);
        try {
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    @Order(1)
    public void setCarInParkValid() {
        var result = setCarInParkCheck("А111АА11", 13, LOGIN);
        result = checkCarInPark("А111АА11", 13, LOGIN) && result;
        assertTrue(result);
    }

    @Test
    @Order(2)
    public void removeCarFromParkInvalidLogin() {
        removeCar(13, "fg");
        assertTrue(checkCarInPark("А111АА11", 13, LOGIN));
    }

    @Test
    @Order(3)
    public void removeCarFromParkValid() {
        removeCar(13, LOGIN);
        assertFalse(checkCarInPark("А111АА11", 13, LOGIN));
    }

    @Test
    public void setCarInParkInvalidCarId() {
        var result = setCarInParkCheck("А111А11", 113, LOGIN);
        result = checkCarInPark("А111А11", 113, LOGIN) && result;
        assertFalse(result);
    }


    @Test
    public void setCarInParkInvalidPlace() {
        var result = setCarInParkCheck("А111АА11", 113, LOGIN);
        result = checkCarInPark("А111АА11", 113, LOGIN) && result;
        assertFalse(result);
    }


    @Test
    public void setCarInParkInvalidLogin() {
        var result = setCarInParkCheck("А111АА11", 113, "fg");
        result = checkCarInPark("А111АА11", 113, "fg") && result;
        assertFalse(result);
    }

    private boolean setCarInParkCheck(String carId, int placeId, String login) {
        var params = new HashMap<String, Object>();
        params.put("Place_id", 13);
        params.put("Login", login);
        params.put("Car_id", carId);
        var query = new LeaveCarQuery();
        query.setParams(params);
        try {
            query.execute();
            return true;
        } catch (PSQLException e) {
            return false;
        } catch (SQLException e) {
            return false;
        }
    }

    private boolean checkCarInPark(String carId, int placeId, String login) {

        var params = new HashMap<String, Object>();
        params.put("Place_id", 13);
        params.put("Login", login);
        params.put("Car_id", carId);

        var query = new UserPlacesQuery();
        query.setParams(params);
        try {
            query.execute();
            return query.getResult().stream()
                    .filter(a -> a.getCarId().equals(carId) && a.getPlaceId() == placeId).count() != 0;
        } catch (SQLException e) {
            return false;
        }
    }

    private void removeCar(int placeId, String login) {
        var params = new HashMap<String, Object>();
        params.put("Place_id", placeId);
        params.put("Login", login);
        var query = new RemoveCarQuery();
        query.setParams(params);
        try {
            query.execute();
        } catch (SQLException e) {}
    }

    @AfterAll
    public void deleteAccount() {
        Map<String, Object> params = new TreeMap<>();
        params.put("Login", "ghjiiidj");
        var query = new DeleteUserQuery();
        query.setParams(params);
        try {
            query.execute();
        } catch (SQLException e) {
        }
    }
}
