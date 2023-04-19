package SQLQuery;

import SQLQuery.CRUDTemplates.SetDataQuery;

import java.util.Map;

public class DeleteUserQuery extends SetDataQuery {
    @Override
    public void setParams(Map<String, Object> params) {
        sql = "DELETE FROM \"user\" WHERE \"Login\" = '" + params.get("Login") + "'";
    }
}
