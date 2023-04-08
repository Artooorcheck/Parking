package SQLQuery;

import Models.ParkPlace;
import SQLQuery.CRUDTemplates.ReadQuery;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UserPlacesQuery extends ReadQuery<List<ParkPlace>> {
    @Override
    public void setParams(Map<String, Object> params) {
        sql = "SELECT pp.\"Place_id\", pp.\"Place_number\", up.\"Car_id\", p.\"Park_adress\" FROM \"user_place\" up\n" +
                "JOIN \"user\" u on u.\"User_id\" = up.\"User_id\"\n" +
                "JOIN \"park_place\" pp on pp.\"Place_id\"=up.\"Place_id\"\n" +
                "JOIN \"parking\" p on p.\"Parking_id\" = pp.\"Park_id\"\n" +
                "WHERE u.\"Login\" = '"+ params.get("login")+"'";
        System.out.println(sql);
    }

    @Override
    public List<ParkPlace> getResult() throws SQLException {
        var places = new LinkedList<ParkPlace>();
        while (result.next()) {
            var place = new ParkPlace();
            place.setCarId(result.getString("Car_id"));
            place.setPlaceId(result.getInt("Place_id"));
            place.setParkAddress(result.getString("Park_adress"));
            place.setPlaceNumber(result.getInt("Place_number"));
            places.add(place);
        }
        return places;
    }
}
