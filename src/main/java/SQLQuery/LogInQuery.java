package SQLQuery;

import SQLQuery.CRUDTemplates.GetDataQuery;

import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

public class LogInQuery extends GetDataQuery<Boolean> {
    public LogInQuery(Properties properties) {
        super(properties);
    }

    @Override
    public void setParams(Map<String, Object> params) {
        sql = "SELECT \"Login\" FROM \"user\" " +
                "WHERE \"Login\" = '" + params.get("Login")
                + "' AND \"Password\" = '"+ params.get("Password") +
                "' FETCH FIRST 1 ROWS ONLY";
    }

    @Override
    public Boolean getResult() throws SQLException {
        return result.next();
    }
}
