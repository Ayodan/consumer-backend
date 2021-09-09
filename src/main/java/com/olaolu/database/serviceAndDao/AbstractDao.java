package com.olaolu.database.serviceAndDao;

import com.olaolu.database.model.BusinessType;

import javax.sql.DataSource;
import java.util.List;

public abstract class AbstractDao {
    public abstract void setDataSource(DataSource var1);
    public abstract void createBusinessType(BusinessType businessType);
    public abstract List<BusinessType> fetchAllBusinessTypes ();
    public abstract BusinessType getBusinessTypeById(int businessTypeId);
    public abstract void updateBusinessType(BusinessType businessType);
}
