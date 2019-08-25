package com.example.backend.model;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Component
public class RoomDaoImpl implements RoomDao {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public List<Room> getAllRooms() {
        return mongoTemplate.findAll(Room.class);
    }

    @Override
    public List<Room> getRoomsByShortPriceRange(BigDecimal up, BigDecimal down) {
        return mongoTemplate.find(Query.query(Criteria.where("short_price").lte(up).gte(down)), Room.class);
    }

    @Override
    public List<Room> getRoomsByLongPriceRange(BigDecimal up, BigDecimal down) {
        return mongoTemplate.find(Query.query(Criteria.where("long_price").lte(up).gte(down)), Room.class);
    }

    @Override
    public List<Room> getRoomsByLocation(String location) {
        return mongoTemplate.find(Query.query(Criteria.where("location").is(location)), Room.class);
    }

    @Override
    public List<Room> getRoomsByCity(String city) {
        return mongoTemplate.find(Query.query(Criteria.where("city").is(city)), Room.class);
    }

    @Override
    public List<Room> getRoomsByType(int type) {
        return mongoTemplate.find(Query.query(Criteria.where("location").is(type)), Room.class);
    }

    @Override
    public List<Room> getRoomsByTypeShortPriceRangeCity(BigDecimal up, BigDecimal down, String city, int type) {
        Query query = Query.query(Criteria.where("").andOperator(
                Criteria.where("short_price").lte(up).gte(down),
                Criteria.where("city").is(city),
                Criteria.where("type").is(type)));
        return mongoTemplate.find(query, Room.class);
}

    @Override
    public List<Room> getRoomsByTypeLongPriceRangeCity(BigDecimal up, BigDecimal down, String city, int type) {
        Query query = Query.query(Criteria.where("").andOperator(
                Criteria.where("long_price").lte(up).gte(down),
                Criteria.where("city").is(city),
                Criteria.where("type").is(type)));
        return mongoTemplate.find(query, Room.class);
    }

    @Override
    public void saveRoom(Room room) {
        mongoTemplate.save(room);
    }

    @Override
    public void updateRoom(Room room) {
        mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(room.getId())),
                new Update().set("location", room.getLocation())
                        .set("short_price", room.getShortPrice())
                        .set("long_price", room.getLongPrice())
                        .set("type", room.getType())
                        .set("hdpi_images", room.getHdpiImages())
                        .set("mdpi_images", room.getMdpiImages())
                        .set("ldpi_images", room.getLdpiImages())
                        .set("is_available", room.isAvailable())
                        .set("description", room.getDescription()), Room.class);
    }

    @Override
    public void deleteRoomById(String id) {
        mongoTemplate.remove(Query.query(Criteria.where("_id").is(id)), Room.class);
    }
}
