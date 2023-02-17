package com.advdb.mongodb.repository;

import com.advdb.mongodb.entity.Hulu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class HuluRepositoryImpl implements HuluRepositoryCustom{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Hulu findById(int id) {

        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Hulu.class);
    }

    @Override
    public void deleteById(int id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Hulu.class);
    }

    public void deleteByTitle(String title) {
    }
}
