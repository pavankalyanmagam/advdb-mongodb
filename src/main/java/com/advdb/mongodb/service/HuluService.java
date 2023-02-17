package com.advdb.mongodb.service;

import com.advdb.mongodb.entity.Hulu;
import com.advdb.mongodb.repository.HuluRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HuluService {

    public HuluService() {
    }

    @Autowired
    private HuluRepository huluRepository;

    public List<Hulu> getAllHulu() {
        return huluRepository.findAll();
    }

    public List<Hulu> getHuluById(Integer id) {
        return huluRepository.findAllById(id);
    }

    public Hulu createHulu(Hulu hulu) {
        return huluRepository.save(hulu);
    }

    public Hulu updateHulu(Integer id, Hulu hulu) {
        Optional<Hulu> optionalHulu = huluRepository.findById(id);
        if (optionalHulu.isPresent()) {
            hulu.setId(id);
            return huluRepository.save(hulu);
        } else {
            return null;
        }
    }

    public void deleteHulu(Integer id) {

        huluRepository.deleteHuluById(id);
    }
}
