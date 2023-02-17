package com.advdb.mongodb.repository;

import com.advdb.mongodb.entity.Hulu;


public interface HuluRepositoryCustom {


    Hulu findById(int id);

    void deleteById(int id);
}
