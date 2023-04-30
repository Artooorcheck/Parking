package SQLQuery;

import SQLQuery.CRUDTemplates.SetDataQuery;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;
import java.util.Properties;

public class DeleteUserQuery extends SetDataQuery {
    public DeleteUserQuery(Properties properties) {
        super(properties);
    }


    public DeleteUserQuery(HttpServletRequest request) {
        super(request);
    }

    @Override
    public void setParams(Map<String, Object> params) {
        sql = "DELETE FROM \"user\" WHERE \"Login\" = '" + params.get("Login") + "'";
    }
}
