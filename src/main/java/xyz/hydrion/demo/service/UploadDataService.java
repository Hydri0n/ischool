package xyz.hydrion.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.hydrion.demo.dao.UploadDataDao;
import xyz.hydrion.demo.domain.UploadData;

@Service
public class UploadDataService {

    private UploadDataDao uploadDataDao;

    @Autowired
    public void setUploadDataDao(UploadDataDao uploadDataDao){
        this.uploadDataDao = uploadDataDao;
    }

    public void upload(UploadData uploadData){
        uploadDataDao.insertData(uploadData);
    }
}
