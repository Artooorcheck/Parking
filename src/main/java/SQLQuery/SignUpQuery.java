package SQLQuery;

import SQLQuery.CRUDTemplates.SetDataQuery;
import Validator.StringValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.ValidationException;

import java.util.Map;
import java.util.Properties;

public class SignUpQuery extends SetDataQuery {

    public SignUpQuery(Properties properties) {
        super(properties);
    }

    public SignUpQuery(HttpServletRequest request) {
        super(request);
    }

    @Override
    public void setParams(Map<String, Object> params) throws ValidationException {
        paramsValidation(params);
        sql = "INSERT INTO public.\"user\"(\"Name\", \"Card_number\", \"Login\", \"Password\") " +
                "VALUES('" + params.get("Name") + "', '" +
                params.get("Card_number") + "', '" + params.get("Login") + "', '" + params.get("Password") + "')";
        System.out.println(sql);
    }

    private void paramsValidation(Map<String, Object> params) throws ValidationException {
        String login = (String) params.get("Login");
        String password = (String) params.get("Password");
        String cardNumber = (String) params.get("Card_number");
        (new StringValidator(login)).lengthMore(5);
        (new StringValidator(password)).lengthMore(5);
        (new StringValidator(cardNumber)).lengthMore(15).lengthLess(19).onlyDigit();
    }
}
