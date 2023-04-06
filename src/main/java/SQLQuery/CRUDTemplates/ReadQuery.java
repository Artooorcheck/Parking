package SQLQuery.CRUDTemplates;

import DBConnection.DBConnection;
import SQLQuery.Interface.ISQLQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public abstract class ReadQuery<TResult> implements ISQLQuery{

    protected String sql;
    protected ResultSet result;

    @Override
    public abstract void setParams(Map<String, Object> params);

    @Override
    public void execute()
    {
        try{
            var connection = DBConnection.getConnection();
            var stmt = connection.createStatement();
            result = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public abstract TResult getResult() throws SQLException;
}
