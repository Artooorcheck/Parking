package SQLQuery.Interface;

import java.sql.SQLException;
import java.util.Map;

public interface ISQLQuery {
    void setParams(Map<String, Object> params);
    void execute() throws SQLException;
}
