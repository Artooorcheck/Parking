package SQLQuery.CRUDTemplates;

import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.Properties;

public abstract class SetDataQuery extends SQLQuery {

    protected String sql;

    public SetDataQuery(Properties properties) {
        super(properties);
    }

    public SetDataQuery(HttpServletRequest request) {
        super(request);
    }

    public void execute() throws SQLException {
        var connectionString = new JSONObject(properties.getProperty("connectionString"));
        var url = connectionString.getString("source")+"/"+connectionString.getString("catalog");
        var user = connectionString.getString("user");
        var password = connectionString.getString("password");
        var connection = getConnection(url, user, password);
        var stmt = connection.createStatement();
        connection.prepareStatement(sql);
        stmt.executeUpdate(sql);
        closeConnection();
    }
}
