package xyz.hydrion.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.hydrion.demo.dao.RoomDao;
import xyz.hydrion.demo.domain.Room;

@Service
public class RoomService {
    private RoomDao roomDao;

    @Autowired
    public void setRoomDao(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public void register(Room room){
        roomDao.insertRoom(room);
    }

    public void update(Room room) {
        roomDao.updateRoom(room);
    }

    public Room findRoomById(String id){
        return roomDao.findRoomByID(id);
    }
}
