package com.olaolu.database.serviceAndDao;

import com.olaolu.database.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @author akano.olanrewaju  @on 09/03/2021
 */
@Repository
public class UserDao {
    private static final String SINGLE_RESULT = "single";
    private DataSource dataSource;
    private SimpleJdbcCall getUserWithName;
    private JdbcTemplate jdbcTemplate=null;

    @Autowired
    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        getUserWithName=new SimpleJdbcCall(dataSource).withProcedureName("getUserByname").
                returningResultSet(SINGLE_RESULT, BeanPropertyRowMapper.newInstance(UserModel.class));
    }

    public List<UserModel> findUserByUserName(String userName){
        MapSqlParameterSource in =
                (new MapSqlParameterSource())
                        .addValue("userName", userName);
        Map<String, Object> getuserByName = getUserWithName.execute(in);
        List<UserModel> users= (List<UserModel>) getuserByName.get(SINGLE_RESULT);
        return users;
    }
}
