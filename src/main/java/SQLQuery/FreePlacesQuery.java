package SQLQuery;

import Models.ParkPlace;
import SQLQuery.CRUDTemplates.GetDataQuery;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class FreePlacesQuery extends GetDataQuery<List<ParkPlace>> {
    public FreePlacesQuery(Properties properties) {
        super(properties);
    }

    public FreePlacesQuery(HttpServletRequest request) {
        super(request);
    }

    @Override
    public void setParams(Map params) {
        sql = "SELECT DISTINCT \"Place_id\", \"Place_number\" FROM \"park_place\"\n" +
                "WHERE \"User_id\" IS NULL AND \"Park_id\" = " + params.get("Park_id") +
                "ORDER BY \"Place_id\"";
    }

    @Override
    public List<ParkPlace> getResult() throws SQLException {
        var places = new ArrayList<ParkPlace>();
        while (result.next()) {
            var place = new ParkPlace();
            place.setPlaceId(result.getInt("Place_id"));
            place.setPlaceNumber(result.getInt("Place_number"));
            places.add(place);
        }
        return places;
    }
}
