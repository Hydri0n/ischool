package xyz.hydrion.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.hydrion.demo.dao.RoomStateDao;
import xyz.hydrion.demo.domain.RoomState;

import java.util.List;

@Service
public class RoomStateService {
    private RoomStateDao roomStateDao;

    public List<RoomState> getDataByType(final String school,final String type){
        return roomStateDao.getDataByType(school,type,1);
    }

    public List<RoomState> getDataByType(final String school,final String type,final int page){
        return roomStateDao.getDataByType(school,type,page);
    }

    public List<RoomState> getAllData(final String school){
        return roomStateDao.getAllData(school,1);
    }

    public List<RoomState> getAllData(final String school,final int page){
        return roomStateDao.getAllData(school,page);
    }

    @Autowired
    public void setRoomStateDao(RoomStateDao roomStateDao) {
        this.roomStateDao = roomStateDao;
    }
}
