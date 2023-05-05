package SQLQuery.CRUDTemplates;

import DBConnection.DBConnection;
import jakarta.xml.bind.ValidationException;

import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

public abstract class SQLQuery extends DBConnection {

    protected  Properties properties;

    public SQLQuery(Properties properties) {
        this.properties = properties;
    }

    public abstract void setParams(Map<String, Object> params) throws ValidationException;
    public abstract void execute() throws SQLException;
}
