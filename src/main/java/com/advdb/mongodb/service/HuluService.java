package com.advdb.mongodb.service;

import com.advdb.mongodb.entity.Hulu;
import com.advdb.mongodb.exception.HuluCollectionException;
import com.advdb.mongodb.repository.HuluRepository;
import com.advdb.mongodb.repository.HuluRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
            throw new HuluCollectionException(HuluCollectionException.NotFoundException(id));
        }

    }

    public Hulu createHulu(Hulu hulu) {
        return huluRepository.save(hulu);
    }

    public Hulu updateHulu(Integer id, Hulu hulu) {
        Optional<Hulu> optionalHulu = Optional.ofNullable(huluRepositoryImpl.findById(id));
        if (optionalHulu.isPresent()) {
            Hulu existingHulu = optionalHulu.get();
            existingHulu.setTitle(hulu.getTitle());
            existingHulu.setClips_count(hulu.getClips_count());
            existingHulu.setDescription(hulu.getDescription());
            existingHulu.setEpisodes_count(hulu.getEpisodes_count());
            existingHulu.setGenres(hulu.getGenres());
            existingHulu.setScore(hulu.getScore());
            existingHulu.setSeasons_count(hulu.getSeasons_count());
            existingHulu.setReleased_at(hulu.getReleased_at());
            existingHulu.setRating(hulu.getRating());

            return huluRepository.save(existingHulu);
        } else {
            throw new HuluCollectionException("Hulu Show with id " + id + " not found");
        }
    }
    public Hulu updateHuluRating(Integer id, Hulu hulu) {
        Optional<Hulu> optionalHulu = Optional.ofNullable(huluRepositoryImpl.findById(id));
        if (optionalHulu.isPresent()) {
            Hulu existingHulu = optionalHulu.get();
            existingHulu.setRating(hulu.getRating());  // update only the rating field
            return huluRepository.save(existingHulu);  // save the updated object
        } else {
            throw new HuluCollectionException("Hulu Show with id " + id + " not found");
        }
    }


    public void deleteHulu(Integer id) {
        Optional<Hulu> optionalHulu = Optional.ofNullable(huluRepositoryImpl.findById(id));
        if (optionalHulu.isPresent()) {
            huluRepositoryImpl.deleteById(id);
        } else {
            throw new HuluCollectionException("Hulu Show with id " + id + " not found");
        }
    }

    public List<Hulu> getHuluByTitle(String title) {
        return huluRepository.findByTitle(title);
    }
}
