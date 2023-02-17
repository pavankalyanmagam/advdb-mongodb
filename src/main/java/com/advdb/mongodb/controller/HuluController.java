package com.advdb.mongodb.controller;

import com.advdb.mongodb.entity.Hulu;
import com.advdb.mongodb.exception.HuluCollectionException;
import com.advdb.mongodb.service.HuluService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/hulu")
public class HuluController {

    @Autowired
    private HuluService huluService;

    // Get All the Hulu Data
    @GetMapping
    public ResponseEntity<?> getAllHulu() {
        List<Hulu> allHulu = huluService.getAllHulu();
        return  new ResponseEntity<>(allHulu, allHulu.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    // Get All the Hulu Data With the Id(Searches for the specified id and return the data with id).
    @GetMapping("/{id}")
    public ResponseEntity<?> getHuluById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(huluService.getHuluById(id), HttpStatus.OK);
        } catch (HuluCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // To Save the New Hulu Data.
    @PostMapping
    public ResponseEntity<?> createHulu(@RequestBody Hulu hulu) {
        try {
            Hulu createdHulu = huluService.createHulu(hulu);
            return new ResponseEntity<Hulu>(createdHulu, HttpStatus.OK);
        } catch (HuluCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

    // Update Hulu Data with given new updated information.
    @PutMapping("/{id}")
    public ResponseEntity<?> updateHulu(@PathVariable Integer id, @RequestBody Hulu hulu) {

        try {
            huluService.updateHulu(id, hulu);
            return new ResponseEntity<>("Updated Hulu Show with id "+id+"", HttpStatus.OK);
        } catch (HuluCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("rating/{id}")
    public ResponseEntity<?> updateHuluRating(@PathVariable Integer id, @RequestBody Hulu hulu) {

        try {
            huluService.updateHuluRating(id, hulu);
            return new ResponseEntity<>("Updated Hulu Show with id "+id+"", HttpStatus.OK);
        } catch (HuluCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    // Delete Hulu data for the given id.
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHulu(@PathVariable Integer id) {
        try{
            huluService.deleteHulu(id);
            return new ResponseEntity<>("Successfully deleted Hulu Show with id "+id, HttpStatus.OK);
        }
        catch (HuluCollectionException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Get Hulu Data Using Title

    @GetMapping("/title/{title}")
    public ResponseEntity<?> getHuluDataByTitle(@PathVariable String title) {

        try {
            return new ResponseEntity<>(huluService.getHuluByTitle(title), HttpStatus.OK);
        } catch (HuluCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
