package SQLQuery;

import SQLQuery.CRUDTemplates.SetDataQuery;
import Validator.CarIdValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.ValidationException;

import java.util.Map;
import java.util.Properties;

public class LeaveCarQuery extends SetDataQuery {
    public LeaveCarQuery(Properties properties) {
        super(properties);
    }

    public LeaveCarQuery(HttpServletRequest request) {
        super(request);
    }

    @Override
    public void setParams(Map<String, Object> params) throws ValidationException {
        paramsValidation(params);
        sql = "UPDATE \"park_place\"\n" +
                "SET \"User_id\" = (SELECT \"User_id\" FROM \"user\" WHERE \"Login\" = '"+ params.get("Login")+"' FETCH FIRST 1 ROWS ONLY), " +
                "\"Car_id\" = '" + params.get("Car_id") + "' " +
                "WHERE \"Place_id\" = " + params.get("Place_id");
    }

    private void paramsValidation(Map<String, Object> params) throws ValidationException {
        String carId = (String) params.get("Car_id");
        int placeId = Integer.parseInt((String) params.get("Place_id"));
        if(placeId > 100) {
            throw new ValidationException("Place_id must be less than 101");
        }
        (new CarIdValidator(carId)).isCarNumber();
    }
}
