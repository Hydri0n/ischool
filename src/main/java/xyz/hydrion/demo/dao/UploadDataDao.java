package xyz.hydrion.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import xyz.hydrion.demo.domain.UploadData;

import java.sql.Date;
import java.sql.Timestamp;

@Repository
public class UploadDataDao {

    private JdbcTemplate jdbcTemplate;
    private final static String INSERT_DATA_SQL = "INSERT INTO " +
            "data(id,count,time) " +
            "VALUES(?,?,?)";

    public void insertData(UploadData uploadData){
        Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
        jdbcTemplate.update(INSERT_DATA_SQL, uploadData.getId(),
                uploadData.getCount(),
                timestamp);
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
}
