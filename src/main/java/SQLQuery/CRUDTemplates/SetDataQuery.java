package SQLQuery.CRUDTemplates;

import DBConnection.DBConnection;
import SQLQuery.Interface.ISQLQuery;
import java.sql.SQLException;
import java.util.Map;

public abstract class SetDataQuery extends DBConnection implements ISQLQuery {

    protected String sql;

    @Override
    public void execute() throws SQLException {
        var connection = getConnection();
        var stmt = connection.createStatement();
        connection.prepareStatement(sql);
        stmt.executeUpdate(sql);
        closeConnection();
    }

    @Override
    public abstract void setParams(Map<String, Object> params);
}
