package SQLQuery.CRUDTemplates;

import SQLQuery.Interface.ISQLQuery;

import java.util.Map;

public abstract class DeleteQuery implements ISQLQuery {
    @Override
    public abstract void execute();

    @Override
    public abstract void setParams(Map<String, Object> params);
}
