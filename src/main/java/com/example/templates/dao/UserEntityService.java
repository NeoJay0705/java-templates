package com.example.templates.dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Repository;

@Repository
public class UserEntityService {
    private NamedParameterJdbcTemplate jdbc;
    private DataSource dataSource;
    
    private UserQuery userQuery;

    private SimpleJdbcCall jdbcCall;

    public UserEntityService(DataSource postgresDB) {
        this.dataSource = postgresDB;
        this.jdbc = new NamedParameterJdbcTemplate(postgresDB);
        this.userQuery = new UserQuery(postgresDB);
        this.jdbcCall = new SimpleJdbcCall(postgresDB);
    }

    public List<UserEntity> getAll() {
        return this.jdbc.query("select * from users", new EmptySqlParameterSource(), (resultSet, rowNum) -> {
            UserEntity user = new UserEntity(resultSet.getString("name"), resultSet.getInt("age"));
            return user;
        });
    }

    public List<UserEntity> getAll2() {
        Map<String, Object> nameds = new HashMap<>();
        nameds.put("name", "aaa");
        nameds.put("age", 19);
        return this.userQuery.executeByNamedParam(nameds);
    }

    public void staticInsert() {
        this.jdbc.update("insert into users values (:name, :age)", new MapSqlParameterSource().addValue("name", "xxx").addValue("age", 99));
    }

    public void batchStaticInsert() {
        UserEntity user1 = new UserEntity("111", 78);
        UserEntity user2 = new UserEntity("222", 79);
        
        MapSqlParameterSource[] users = transform(new UserEntity[] {user1, user2});
        // new JdbcTemplate().batchUpdate(sql, batchArgs, batchSize, pss)
        this.jdbc.batchUpdate("insert into users values (:name, :age)", users);
    }

    private MapSqlParameterSource[] transform(UserEntity[] users) {
        MapSqlParameterSource[] userSources = new MapSqlParameterSource[users.length];
        
        for (int i = 0; i < users.length; i++) {
            userSources[i] = new MapSqlParameterSource();
            for (Field f : users[i].getClass().getDeclaredFields()) {
                try {
                    f.setAccessible(true);
                    String key = f.getName();
                    Object value = f.get(users[i]);
                    userSources[i].addValue(key, value);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            } 
        }
        return userSources;
    }

    public List<UserEntity> callSP() {
        
        SimpleJdbcCall jdbcCall =  new SimpleJdbcCall(this.dataSource).withProcedureName("select_users5")
            // .withoutProcedureColumnMetaDataAccess()
            // .useInParameterNames("b")
            // .declareParameters(new SqlParameter("b", Types.INTEGER))
            .returningResultSet("out", BeanPropertyRowMapper.newInstance(UserEntity.class));
        return this.jdbc.query("select * from test4()", new HashMap<String, Object>(0), BeanPropertyRowMapper.newInstance(UserEntity.class));
        // Map<String, Object> inParamMap = new HashMap<String, Object>();
        // inParamMap.put("a", "111");
        // inParamMap.put("b", 78);
        // SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        // return this.jdbc.query("select select_users2(:a, :b)", new MapSqlParameterSource().addValue("a", "111").addValue("b", 78), (resultSet, rowNum) -> {
        //     UserEntity user = new UserEntity(resultSet.getString("select_users2"), 0);
        //     return user;
        // });
        // return this.jdbc.queryForList("select select_users2(:a, :b)", new MapSqlParameterSource().addValue("a", "111").addValue("b", 78), UserEntity.class);
        // Map<String, Object> simpleJdbcCallResult = jdbcCall.execute(in);
        // jdbcCall.
        // return jdbcCall.executeFunction(List.class, new MapSqlParameterSource().addValue("a", "111").addValue("b", 78));
        
        // return simpleJdbcCallResult;
        // new JdbcTemplate(this.dataSource).ex
    }

    /*
     * Like a storedProcedure by Java
     */
    class UserQuery extends MappingSqlQuery<UserEntity> {

        public UserQuery(DataSource postgresDB) {
            super(postgresDB, "select * from users where name = :name and age = :age");
            declareParameter(new SqlParameter("name", Types.VARCHAR));
            declareParameter(new SqlParameter("age", Types.INTEGER));
            compile();
        }

        @Override
        protected UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserEntity user = new UserEntity(rs.getString("name"), rs.getInt("age"));
            return user;
        }
        
    }

}
