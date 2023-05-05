package SQLQuery;

import SQLQuery.CRUDTemplates.SetDataQuery;

import java.util.Map;
import java.util.Properties;

public class DeleteUserQuery extends SetDataQuery {
    public DeleteUserQuery(Properties properties) {
        super(properties);
    }

    @Override
    public void setParams(Map<String, Object> params) {
        sql = "DELETE FROM \"user\" WHERE \"Login\" = '" + params.get("Login") + "'";
    }
}
