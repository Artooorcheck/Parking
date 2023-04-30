package SQLQuery.CRUDTemplates;

import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public abstract class GetDataQuery<TResult> extends SQLQuery {

    protected String sql;
    protected ResultSet result;

    public GetDataQuery(Properties properties) {
        super(properties);
    }

    public GetDataQuery(HttpServletRequest request) {
        super(request);
    }

    public void execute() throws SQLException {
        var connectionSTR = properties.getProperty("connectionString");
        var connectionString = new JSONObject(connectionSTR);
        var url = connectionString.getString("source")+"/"+connectionString.getString("catalog");
        var user = connectionString.getString("user");
        var password = connectionString.getString("password");
        var connection = getConnection(url, user, password);
        var stmt = connection.createStatement();
        result = stmt.executeQuery(sql);
        closeConnection();
    }

    public abstract TResult getResult() throws SQLException;
}
