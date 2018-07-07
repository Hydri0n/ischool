package xyz.hydrion.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import xyz.hydrion.demo.domain.RoomState;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class RoomStateDao {
    private JdbcTemplate jdbcTemplate;
    private final static int PAGE_COUNT = 8;
    private final static String QUERY_BY_TYPE_SQL = "SELECT id,roomname,count,sum,max(time) AS time FROM " +
            "(SELECT * from room,data where school=? AND type=? " +
            "and room.devid=data.id) a " +
            "GROUP BY a.id ORDER BY id";
    private final static String QUERY_ALL = "SELECT id,roomname,count,sum,type,max(time) AS time FROM " +
            "(SELECT * from room,data where school=?" +
            "and room.devid=data.id) a " +
            "GROUP BY a.id ORDER BY id";

    public List<RoomState> getDataByType(final String school, final String type,final int page){
        final List<RoomState> list = new ArrayList<RoomState>();
        jdbcTemplate.query(QUERY_BY_TYPE_SQL, new Object[]{school,type}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                int startIndex = (page - 1) * PAGE_COUNT;
                for (int i = 0;i < startIndex + PAGE_COUNT;i++){
                    if (i >= startIndex)
                    {
                        RoomState roomState = new RoomState();
                        roomState.setId(resultSet.getString("id"));
                        roomState.setName(resultSet.getString("roomname"));
                        roomState.setCount(resultSet.getInt("count"));
                        roomState.setSum(resultSet.getInt("sum"));
                        roomState.setTime(resultSet.getTimestamp("time"));
                        roomState.setType(type);
                        roomState.setSchool(school);
                        list.add(roomState);
                    }
                    if (!resultSet.next()) break;
                }
                while (resultSet.next());
            }
        });
        return list;
    }

    public List<RoomState> getAllData(final String school,final int page){
        final List<RoomState> list = new ArrayList<RoomState>();
//        final int i = (page - 1) * PAGE_COUNT;
        jdbcTemplate.query(QUERY_ALL, new Object[]{school}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                int startIndex = (page - 1) * PAGE_COUNT;
                for (int i = 0;i < startIndex + PAGE_COUNT;i++){
                    if (i >= startIndex)
                    {
                        RoomState roomState = new RoomState();
                        roomState.setId(resultSet.getString("id"));
                        roomState.setName(resultSet.getString("roomname"));
                        roomState.setCount(resultSet.getInt("count"));
                        roomState.setSum(resultSet.getInt("sum"));
                        roomState.setTime(resultSet.getTimestamp("time"));
                        roomState.setType(resultSet.getString("type"));
                        roomState.setSchool(school);
                        list.add(roomState);
                    }
                    if (!resultSet.next()) break;
                }
                while (resultSet.next());
//                do {
//
//                } while (resultSet.next());
            }
        });
        return list;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
