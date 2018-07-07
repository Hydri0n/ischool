package xyz.hydrion.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import xyz.hydrion.demo.domain.BaseResponse;
import xyz.hydrion.demo.domain.Room;
import xyz.hydrion.demo.service.RoomService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
public class RoomController {
    private RoomService roomService;

    @RequestMapping(value = "/room/register",method = RequestMethod.POST)
    public BaseResponse registerRoom(@RequestBody Room room){
        BaseResponse result = new BaseResponse();
        roomService.register(room);
        result.setCode(200);
        result.setStatus("ok");
        result.setMessage("register success");
        return result;
    }

    @RequestMapping(value = "/room/update",method = RequestMethod.POST)
    public BaseResponse updateRoom(@RequestBody Room room){
        BaseResponse result = new BaseResponse();
        roomService.update(room);
        result.setCode(200);
        result.setStatus("ok");
        result.setMessage("register success");
        return result;
    }

    @RequestMapping(value = "/room/search",method = RequestMethod.GET)
    public Room searchById(@RequestParam String id, HttpServletResponse response) throws IOException {
        Room result = roomService.findRoomById(id);
        if (result.getName() == null){
            response.sendRedirect("/ischool/errorpage");
        }
        return result;
    }

    @RequestMapping(value = "/errorpage")
    public BaseResponse error(HttpServletResponse response){
        BaseResponse result = new BaseResponse();
        result.setCode(404);
        result.setStatus("error");
        result.setMessage("not found!");
        response.setStatus(404);
        return result;
    }

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }
}
