package SQLQuery;

import Models.ParkPlace;
import SQLQuery.CRUDTemplates.GetDataQuery;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UserPlacesQuery extends GetDataQuery<List<ParkPlace>> {
    @Override
    public void setParams(Map<String, Object> params) {
        sql = "SELECT pp.\"Place_id\", pp.\"Place_number\", pp.\"Car_id\", p.\"Park_address\" FROM \"park_place\" pp\n" +
                "JOIN \"user\" u on u.\"User_id\" = pp.\"User_id\"\n" +
                "JOIN \"parking\" p on p.\"Parking_id\" = pp.\"Park_id\" " +
                "WHERE u.\"Login\" = '"+ params.get("Login")+"'";
    }

    @Override
    public List<ParkPlace> getResult() throws SQLException {
        var places = new LinkedList<ParkPlace>();
        while (result.next()) {
            var place = new ParkPlace();
            place.setCarId(result.getString("Car_id"));
            place.setPlaceId(result.getInt("Place_id"));
            place.setParkAddress(result.getString("Park_address"));
            place.setPlaceNumber(result.getInt("Place_number"));
            places.add(place);
        }
        return places;
    }
}
