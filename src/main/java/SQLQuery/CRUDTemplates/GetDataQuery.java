package SQLQuery.CRUDTemplates;

import DBConnection.DBConnection;
import SQLQuery.Interface.ISQLQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public abstract class GetDataQuery<TResult> extends DBConnection implements ISQLQuery{

    protected String sql;
    protected ResultSet result;

    @Override
    public abstract void setParams(Map<String, Object> params);

    @Override
    public void execute() throws SQLException {
        var connection = getConnection();
        var stmt = connection.createStatement();
        result = stmt.executeQuery(sql);
        closeConnection();
    }

    public abstract TResult getResult() throws SQLException;
}
