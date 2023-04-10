package SQLQuery;

import Models.Park;
import SQLQuery.CRUDTemplates.ReadQuery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AvailibleParkQuery extends ReadQuery<List<Park>> {

    public AvailibleParkQuery() {
        sql = "SELECT DISTINCT p.\"Parking_id\", p.\"Park_address\" FROM \"park_place\" pp\n" +
                "JOIN \"parking\" p on p.\"Parking_id\" = pp.\"Park_id\"\n" +
                "WHERE pp.\"User_id\" IS NULL";
    }

    @Override
    public void setParams(Map params) {
    }

    @Override
    public List<Park> getResult() throws SQLException {
        var parks = new ArrayList<Park>();
        while (result.next()) {
            var park = new Park();
            park.setParkId(result.getInt("Parking_id"));
            park.setParkName(result.getString("Park_address"));
            parks.add(park);
        }
        return parks;
    }
}
