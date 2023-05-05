package Services;

import SQLQuery.DeleteUserQuery;
import SQLQuery.LogInQuery;
import SQLQuery.SignUpQuery;
import jakarta.xml.bind.ValidationException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

public class AuthorizationService extends Service{

    public AuthorizationService(Properties properties) {
        super(properties);
    }

    public void deleteUser(String login) throws SQLException {
        var mapper = new DeleteUserQuery(properties);
        var params = new HashMap<String, Object>();
        params.put("Login", login);
        mapper.setParams(params);
        mapper.execute();
    }

    public boolean loginIsExist(String login, String password) throws SQLException {
        var mapper = new LogInQuery(properties);
        var params = new HashMap<String, Object>();
        params.put("Login", login);
        params.put("Password", password);
        mapper.setParams(params);
        mapper.execute();
        return mapper.getResult();
    }

    public void createUser(String login, String password, String cardNumber, String name) throws ValidationException, SQLException {
        var mapper = new SignUpQuery(properties);
        var params = new HashMap<String, Object>();
        params.put("Password", password);
        params.put("Login", login);
        params.put("Card_number", cardNumber);
        params.put("Name", name);
        mapper.setParams(params);
        mapper.execute();
    }
}
