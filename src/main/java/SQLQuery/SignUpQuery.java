package SQLQuery;

import SQLQuery.CRUDTemplates.SetDataQuery;

import java.util.Map;

public class SignUpQuery extends SetDataQuery {

    @Override
    public void setParams(Map<String, Object> params) {
        sql = "INSERT INTO public.\"user\"(\"User_id\", \"Name\", \"Card_number\", \"Login\", \"Password\") " +
                "VALUES((SELECT COALESCE(MAX(\"User_id\"), 0)+1 FROM \"user\"), '" +
                params.get("Name") + "', '" + params.get("Card_number") + "', '" + params.get("Login") + "', '" + params.get("Password") + "')";
    }
}
