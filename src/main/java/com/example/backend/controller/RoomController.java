package com.example.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.backend.model.Room;
import com.example.backend.model.RoomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class RoomController {

    @Autowired
    private RoomDao roomDao;

    @GetMapping("search_rooms_by_short_price_range")
    public List<Room> searchRoomsByShortPriceRange(HttpServletRequest request) {
        BigDecimal up = new BigDecimal(request.getParameter("up"));
        BigDecimal down = new BigDecimal(request.getParameter("down"));
        return roomDao.getRoomsByShortPriceRange(up, down);
    }

    @GetMapping("search_rooms_by_long_price_range")
    public List<Room> searchRoomsByLongPriceRange(HttpServletRequest request) {
        BigDecimal up = new BigDecimal(request.getParameter("up"));
        BigDecimal down = new BigDecimal(request.getParameter("down"));
        return roomDao.getRoomsByLongPriceRange(up, down);
    }

    @GetMapping("search_rooms_by_location")
    public List<Room> searchRoomsByLocation(HttpServletRequest request) {
        String location = request.getParameter("location");
        return roomDao.getRoomsByLocation(location);
    }

    @GetMapping("search_rooms_by_city")
    public List<Room> searchRoomsByCity(HttpServletRequest request) {
        String location = request.getParameter("city");
        return roomDao.getRoomsByCity(location);
    }

    @GetMapping("search_rooms_by_type")
    public List<Room> searchRoomsByType(HttpServletRequest request) {
        int type = Integer.parseInt(request.getParameter("type"));
        return roomDao.getRoomsByType(type);
    }

    @GetMapping("search_rooms_by_type_short_price_range_city")
    public List<Room> searchRoomsByTypeShortPriceRangeCity(HttpServletRequest request) {
        int type = Integer.parseInt(request.getParameter("type"));
        String city = request.getParameter("city");
        BigDecimal up = new BigDecimal(request.getParameter("up"));
        BigDecimal down = new BigDecimal(request.getParameter("down"));
        return roomDao.getRoomsByTypeShortPriceRangeCity(up, down, city, type);
    }

    @GetMapping("search_rooms_by_type_long_price_range_city")
    public List<Room> searchRoomsByTypeLongPriceRangeCity(HttpServletRequest request) {
        int type = Integer.parseInt(request.getParameter("type"));
        String city = request.getParameter("city");
        BigDecimal up = new BigDecimal(request.getParameter("up"));
        BigDecimal down = new BigDecimal(request.getParameter("down"));
        return roomDao.getRoomsByTypeLongPriceRangeCity(up, down, city, type);
    }

    @GetMapping("get_all_rooms")
    public List<Room> getAllRooms(){
        return roomDao.getAllRooms();
    }

    @PostMapping("add_room")
    public String addRoom(@RequestBody String request) {
        Room room = JSONObject.parseObject(request, Room.class);
        room.setAvailable(true);
        room.setMark(5.0);
        roomDao.saveRoom(room);
        return "success";
    }

    @PostMapping("update_room")
    public String updateRoom(@RequestBody String request){
        Room room = JSONObject.parseObject(request, Room.class);
        roomDao.updateRoom(room);
        return "success";
    }

    @PostMapping("delete_room_by_id")
    public String deleteRoom(@RequestBody String request){
        String id = new org.json.JSONObject(request).getString("id");
        roomDao.deleteRoomById(id);
        return "success";
    }

}
