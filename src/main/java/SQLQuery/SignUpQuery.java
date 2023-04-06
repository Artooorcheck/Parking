package SQLQuery;

import SQLQuery.CRUDTemplates.CreateQuery;

import java.util.Map;

public class SignUpQuery extends CreateQuery {

    @Override
    public void setParams(Map<String, Object> params) {
        sql = "INSERT INTO public.\"user\"(\"User_id\", \"Name\", \"Card_number\", \"Login\", \"Password\") " +
                "VALUES((SELECT COALESCE(MAX(\"User_id\"), 0)+1 FROM \"user\"), '" +
                params.get("userName") + "', '" + params.get("cardNumber") + "', '" + params.get("login") + "', '" + params.get("password") + "')";
    }
}
