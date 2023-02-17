package com.advdb.mongodb.service;

import com.advdb.mongodb.entity.Hulu;
import com.advdb.mongodb.exception.HuluCollectionException;
import com.advdb.mongodb.repository.HuluRepository;
import com.advdb.mongodb.repository.HuluRepositoryImpl;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HuluService {

    public HuluService() {
    }

    @Autowired
    private HuluRepository huluRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private HuluRepositoryImpl huluRepositoryImpl;

    public List<Hulu> getAllHulu() {
        List<Hulu> allHulu = huluRepository.findAll();
        if(allHulu.size() > 0)
            return allHulu;
        else
            return new ArrayList<Hulu>();

    }

    public List<Hulu> getHuluById(Integer id) {
        List<Hulu> allById = huluRepository.findAllById(id);
        if (allById.size() > 0) {
            return allById;
        }
        else {
            throw new HuluCollectionException(HuluCollectionException.NotFoundException(String.valueOf(id)));
        }

    }

    public Hulu createHulu(Hulu hulu) {
        return huluRepository.save(hulu);
    }


    public void deleteHulu(String title) {
            Query query = new Query(Criteria.where("title").is(title));
            DeleteResult result = mongoTemplate.remove(query, Hulu.class);
            if (result.getDeletedCount() == 0) {
                throw new HuluCollectionException("Hulu show with title " + title + " not found");
            }
    }


    public List<Hulu> getHuluByTitle(String title) {
        List<Hulu> allByTitle = huluRepository.findByTitle(title);
        if (allByTitle.size() > 0) {
            return allByTitle;
        }
        else {
            throw new HuluCollectionException(HuluCollectionException.NotFoundException(title));
        }
    }

    public void updateHuluByTitle(String title, Hulu hulu) {
        List<Hulu> byTitle = huluRepository.findByTitle(title);
        if (!byTitle.isEmpty()) {
            Hulu existingHulu = byTitle.get(0);
            existingHulu.setId(hulu.getId());
            existingHulu.setTitle(hulu.getTitle());
            existingHulu.setDescription(hulu.getDescription());
            existingHulu.setScore(hulu.getScore());
            existingHulu.setRating(hulu.getRating());

            huluRepository.save(existingHulu);
        } else {
            throw new HuluCollectionException("Hulu Show with title " + title + " not found");
        }
    }

}
