package SQLQuery.CRUDTemplates;

import DBConnection.DBConnection;
import SQLQuery.Interface.ISQLQuery;
import java.sql.SQLException;
import java.util.Map;

public abstract class SetDataQuery implements ISQLQuery {

    protected String sql;

    @Override
    public void execute()
    {
        try {
            var connection = DBConnection
                    .getConnection();
            var stmt = connection.createStatement();
            connection.prepareStatement(sql);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public abstract void setParams(Map<String, Object> params);
}
