package SQLQuery;

import SQLQuery.CRUDTemplates.SetDataQuery;

import java.util.Map;

public class RemoveCarQuery extends SetDataQuery {
    @Override
    public void setParams(Map<String, Object> params) {
        sql = "UPDATE \"park_place\" " +
                "SET \"User_id\" = NULL, " +
                "\"Car_id\" = NULL " +
                "WHERE \"Place_id\" = " + params.get("Place_id");
        System.out.println(sql);
    }
}
