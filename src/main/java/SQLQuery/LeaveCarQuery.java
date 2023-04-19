package SQLQuery;

import SQLQuery.CRUDTemplates.SetDataQuery;

import java.util.Map;

public class LeaveCarQuery extends SetDataQuery {
    @Override
    public void setParams(Map<String, Object> params) {
        sql = "UPDATE \"park_place\"\n" +
                "SET \"User_id\" = (SELECT \"User_id\" FROM \"user\" WHERE \"Login\" = '"+ params.get("Login")+"' FETCH FIRST 1 ROWS ONLY), " +
                "\"Car_id\" = '" + params.get("Car_id") + "' " +
                "WHERE \"Place_id\" = " + params.get("Place_id");
    }
}
