package xyz.hydrion.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.hydrion.demo.domain.BaseResponse;
import xyz.hydrion.demo.domain.UploadData;
import xyz.hydrion.demo.service.UploadDataService;

@RestController
public class DataController {

    private UploadDataService uploadDataService;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public BaseResponse upload(@RequestBody UploadData uploadData){
        BaseResponse result = new BaseResponse();
        //TODO: 判断数据真实性

        uploadDataService.upload(uploadData);
        result.setCode(200);
        result.setStatus("ok");
        result.setMessage("Upload successfully.");
        return result;
    }

    @RequestMapping(value = "/upload",method = RequestMethod.GET)
    public BaseResponse upload(@RequestParam("id") String id,
                               @RequestParam("count") int count){
        BaseResponse result = new BaseResponse();

        UploadData uploadData = new UploadData();
        uploadData.setCount(count);
        uploadData.setId(id);
        uploadDataService.upload(uploadData);
        result.setCode(200);
        result.setStatus("ok");
        result.setMessage("Upload successfully.");
        return result;
    }

    @Autowired
    public void setUploadDataService(UploadDataService uploadDataService){
        this.uploadDataService = uploadDataService;
    }
}
