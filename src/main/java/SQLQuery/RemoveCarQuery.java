package SQLQuery;

import SQLQuery.CRUDTemplates.SetDataQuery;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;
import java.util.Properties;

public class RemoveCarQuery extends SetDataQuery {
    public RemoveCarQuery(Properties properties) {
        super(properties);
    }

    public RemoveCarQuery(HttpServletRequest request) {
        super(request);
    }

    @Override
    public void setParams(Map<String, Object> params) {
        sql = "UPDATE \"park_place\" " +
                "SET \"User_id\" = NULL, " +
                "\"Car_id\" = NULL" +
                " WHERE \"Place_id\" = " + params.get("Place_id") +
                " AND \"User_id\" = (SELECT \"User_id\" FROM \"user\" WHERE \"Login\" = '" + params.get("Login") +
                "' FETCH FIRST 1 ROWS ONLY)";
    }
}
