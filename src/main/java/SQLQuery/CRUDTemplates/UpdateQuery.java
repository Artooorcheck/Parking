package SQLQuery.CRUDTemplates;

import SQLQuery.Interface.ISQLQuery;

import java.util.Map;

public abstract class UpdateQuery implements ISQLQuery{
    @Override
    public abstract void setParams(Map<String, Object> params);

    @Override
    public abstract void  execute();
}
