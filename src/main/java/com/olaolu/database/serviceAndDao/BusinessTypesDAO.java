package com.olaolu.database.serviceAndDao;

import com.olaolu.database.model.BusinessType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class BusinessTypesDAO extends AbstractDao{
    private static final String SINGLE_RESULT = "single";
    private static final String MULTIPLE_RESULT = "multiple";
    SimpleJdbcCall simpleJdbcCall;

    Logger logger=LoggerFactory.getLogger(BusinessTypesDAO.class);

    @Autowired
    @Override
    public void setDataSource(DataSource dataSource) {
        simpleJdbcCall=new SimpleJdbcCall(dataSource);

    }

    @Override
    public void createBusinessType(BusinessType businessType) {
        MapSqlParameterSource in =
                (new MapSqlParameterSource())
                        .addValue("id", businessType.getId())
                        .addValue("name", businessType.getName())
                        .addValue("description", businessType.getDescription());
        Map<String, Object> objectMap = simpleJdbcCall.withProcedureName("create_business_type").withReturnValue().execute(in);
        int id= (int) objectMap.get("id");
        businessType.setId(id);



    }

    @Override
    public List<BusinessType> fetchAllBusinessTypes() {
        Map<String, Object> fetchAllBusinesstype = simpleJdbcCall.withProcedureName("fetch_all_business_type").
                returningResultSet(MULTIPLE_RESULT, BeanPropertyRowMapper.newInstance(BusinessType.class)).execute();
        return (List<BusinessType>) fetchAllBusinesstype.get(MULTIPLE_RESULT);
    }

    @Override
    public BusinessType getBusinessTypeById(int businessTypeId) {
        MapSqlParameterSource in =
                (new MapSqlParameterSource())
                        .addValue("id", businessTypeId);
        Map<String, Object> getBusinesstypebyId = simpleJdbcCall.withProcedureName("fetch_single_business_type").
                returningResultSet(SINGLE_RESULT, BeanPropertyRowMapper.newInstance(BusinessType.class)).execute(in);
        List<BusinessType> businesses = (List<BusinessType>) getBusinesstypebyId.get(SINGLE_RESULT);
        if (businesses.size()==0){
            return null;
        }else {
            return businesses.get(0);
        }

    }


    @Override
    public void updateBusinessType(BusinessType businessType) {
        MapSqlParameterSource in =
                (new MapSqlParameterSource())
                        .addValue("id", businessType.getId())
                        .addValue("name", businessType.getName())
                        .addValue("description", businessType.getDescription());
        simpleJdbcCall.withProcedureName("update_business_type").withReturnValue().execute(in);

    }
}
