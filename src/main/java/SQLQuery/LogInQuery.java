package SQLQuery;

import SQLQuery.CRUDTemplates.GetDataQuery;

import java.sql.SQLException;
import java.util.Map;

public class LogInQuery extends GetDataQuery<Boolean> {
    @Override
    public void setParams(Map<String, Object> params) {
        sql = "SELECT \"Login\" FROM \"user\" " +
                "WHERE \"Login\" = '" + params.get("login")
                + "' AND \"Password\" = '"+ params.get("password") +
                "' FETCH FIRST 1 ROWS ONLY";
    }

    @Override
    public Boolean getResult() throws SQLException {
        return result.next();
    }
}
