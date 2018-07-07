package xyz.hydrion.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.hydrion.demo.domain.RoomState;
import xyz.hydrion.demo.service.RoomStateService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
public class RoomStateController {
    private RoomStateService roomStateService;

    @RequestMapping(value = "/state",method = RequestMethod.GET)
    public List<RoomState> getRoomState(@RequestParam String school,
                                        @RequestParam(required = false) String type,
                                        @RequestParam(required = false) Integer page,
                                        HttpServletResponse response) throws IOException {
        List<RoomState> list;

        if (type == null){
            if (page == null || page <= 0){
                list = roomStateService.getAllData(school);
            }
            else list = roomStateService.getAllData(school,page);
        }
        else{
            if (page == null || page <= 0){
                list = roomStateService.getDataByType(school,type);
            }
            else list = roomStateService.getDataByType(school,type,page);
        }
        if (list.isEmpty()){
            response.sendRedirect("/ischool/errorpage");
        }
        return list;
    }

    @RequestMapping(value = "/roomImage/{id}",method = RequestMethod.GET)
    public void downloadFileFromSysDir(HttpServletResponse response, @PathVariable("id") String id) {
        String fileName = id + ".jpg";
        response.setHeader("content-type","application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            File file = new File("C://image/room/"+ fileName);
            if (!file.exists())
                response.sendRedirect("/ischool/errorpage");
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Autowired
    public void setRoomStateService(RoomStateService roomStateService) {
        this.roomStateService = roomStateService;
    }
}
