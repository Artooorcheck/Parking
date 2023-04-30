package SQLQuery.CRUDTemplates;

import DBConnection.DBConnection;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.ValidationException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

public abstract class SQLQuery extends DBConnection {

    protected  Properties properties;

    public SQLQuery(Properties properties) {
        this.properties = properties;
    }

    public SQLQuery(HttpServletRequest request) {
        properties = new Properties();
        try {
            properties.load(request.getServletContext().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void setParams(Map<String, Object> params) throws ValidationException;
    public abstract void execute() throws SQLException;
}
