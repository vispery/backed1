package com.example.backend.model;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TenantDaoImpl implements TenantDao {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void saveTenant(Tenant tenant) {
        mongoTemplate.save(tenant);
    }

    @Override
    public boolean isValidName(String newName) {
        return mongoTemplate.findOne(new Query(Criteria.where("name").is(newName)),Tenant.class)==null;
    }

    @Override
    public boolean isValidEmail(String email) {
        return mongoTemplate.findOne(new Query(Criteria.where("email").is(email)),Tenant.class)==null;
    }

    @Override
    public String getPasswordByEmail(String email) {
        return mongoTemplate.findOne(new Query(Criteria.where("email").is(email)),Tenant.class).getPassword();
    }

    @Override
    public String getPasswordByName(String name) {
        return mongoTemplate.findOne(new Query(Criteria.where("name").is(name)),Tenant.class).getPassword();
    }

    @Override
    public Tenant getTenantByName(String name) {
        return mongoTemplate.findOne(new Query(Criteria.where("name").is(name)),Tenant.class);
    }

    @Override
    public void activeTenant(String id) {
        mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(id)), new Update().set("is_active", true), Tenant.class);
}

    @Override
    public Tenant getTenantById(String id) {
        return mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)),Tenant.class);
    }

    @Override
    public Tenant getTenantByEmail(String email) {
        return mongoTemplate.findOne(new Query(Criteria.where("email").is(email)),Tenant.class);
    }
}
