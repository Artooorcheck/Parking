package SQLQuery;

import Models.ParkPlace;
import SQLQuery.CRUDTemplates.ReadQuery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FreePlacesQuery extends ReadQuery<List<ParkPlace>> {
    @Override
    public void setParams(Map params) {
        sql = "SELECT DISTINCT \"Place_id\", \"Place_number\" FROM \"park_place\"\n" +
                "WHERE \"User_id\" IS NULL AND \"Park_id\" = " + params.get("Park_id");
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
