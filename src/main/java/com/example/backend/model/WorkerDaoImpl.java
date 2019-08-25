package com.example.backend.model;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class WorkerDaoImpl implements WorkerDao{
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void saveWorker(Worker worker) {
        mongoTemplate.save(worker);
    }

    @Override
    public void deleteWorker(String id) {
        mongoTemplate.remove(Query.query(Criteria.where("_id").is(id)), Worker.class);
    }

    @Override
    public void updateName(String id, String name) {
        mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(id)), new Update().set("name", name), Worker.class);
    }

    @Override
    public void updateImage(String id, String image) {
        mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(id)), new Update().set("image", image), Worker.class);
    }

    @Override
    public void updateMark(String id, double mark) {
        mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(id)), new Update().set("mark", mark), Worker.class);
    }

    @Override
    public Worker getWorkerById(String id) {
        return mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)),Worker.class);
    }
}
