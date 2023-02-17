package com.advdb.mongodb.repository;

import com.advdb.mongodb.entity.Hulu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;


public interface HuluRepository extends MongoRepository<Hulu, Integer> {
    List<Hulu> findByTitle(String title);

    @Query("{'id': ?0}")
    List<Hulu> findAllById(Integer id);
}
