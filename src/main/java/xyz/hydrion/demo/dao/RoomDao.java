package xyz.hydrion.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import xyz.hydrion.demo.domain.Room;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class RoomDao {
    private JdbcTemplate jdbcTemplate;
    private static final String INSERT_SQL = "INSERT INTO " +
            "room(devid,roomname,sum,type,school) " +
            "VALUES(?,?,?,?,?)";
    private static final String UPDATE_SQL = "UPDATE room SET " +
            "roomname=?,sum=?,type=?,school=? " +
            "WHERE devid=?";
    private static final String QUERY_BY_DEVID = "SELECT roomname,sum,type,school " +
            "FROM room WHERE devid=?";

   public void insertRoom(Room room){
        jdbcTemplate.update(INSERT_SQL,
                room.getId(),room.getName(),room.getCount(),
                room.getType(),room.getSchool());
   }

   public void updateRoom(Room room){
        jdbcTemplate.update(UPDATE_SQL,room.getName(),room.getCount(),
                room.getType(),room.getSchool(),room.getId());
   }

   public Room findRoomByID(final String id){
       final Room room = new Room();
       jdbcTemplate.query(QUERY_BY_DEVID, new Object[]{id}, new RowCallbackHandler() {
           @Override
           public void processRow(ResultSet resultSet) throws SQLException {
               room.setId(id);
               room.setName(resultSet.getString("roomname"));
               room.setCount(resultSet.getInt("sum"));
               room.setType(resultSet.getString("type"));
               room.setSchool(resultSet.getString("school"));
           }


       });
       return room;
   }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
}
