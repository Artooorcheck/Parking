package Services;

import Models.Park;
import Models.ParkPlace;
import SQLQuery.*;
import jakarta.xml.bind.ValidationException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class CarService extends Service {


    public CarService(Properties properties) {
        super(properties);
    }

    public void leaveCar(String placeId, String login, String carId) throws ValidationException, SQLException {
        var mapper = new LeaveCarQuery(properties);
        var params = new HashMap<String, Object>();
        params.put("Place_id", placeId);
        params.put("Login", login);
        params.put("Car_id", carId);
        mapper.setParams(params);
        mapper.execute();
    }

    public List<Park> getAvailableParks() throws SQLException {
        var mapper = new AvailableParkQuery(properties);
        mapper.execute();
        return mapper.getResult();
    }

    public List<ParkPlace> getFreePlaces(String parkId) throws SQLException {
        var mapper = new FreePlacesQuery(properties);
        var params = new HashMap<String, Object>();
        params.put("Park_id", parkId);
        mapper.setParams(params);
        mapper.execute();
        return mapper.getResult();
    }

    public void removeCar(String placeId, String login) throws SQLException {
        var mapper = new RemoveCarQuery(properties);
        var params = new HashMap<String, Object>();
        params.put("Place_id", placeId);
        params.put("Login", login);
        mapper.setParams(params);
        mapper.execute();
    }

    public List<ParkPlace> getUserCars(String login) throws SQLException {
        var mapper = new UserPlacesQuery(properties);
        var params = new HashMap<String, Object>();
        params.put("Login", login);
        mapper.setParams(params);
        mapper.execute();
        return mapper.getResult();
    }
}
