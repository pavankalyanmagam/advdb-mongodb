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
public class HuluController {

    @Autowired
    private HuluService huluService;

    // 1.	Insert the new movie and show.
    @PostMapping("api/hulu")
    public ResponseEntity<?> createHulu(@RequestBody Hulu hulu) {
        try {
            Hulu createdHulu = huluService.createHulu(hulu);
            return new ResponseEntity<Hulu>(createdHulu, HttpStatus.OK);
        } catch (HuluCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

    // 2.	Update the movie and show information using title. (By update only id, title, description, score, and rating)

    @PutMapping("/api/hulu/{title}")
    public ResponseEntity<?> updateHulu(@PathVariable String  title, @RequestBody Hulu hulu) {

        try {
            huluService.updateHuluByTitle(title, hulu);
            return new ResponseEntity<>("Updated Hulu Show/Movie with title "+title+"", HttpStatus.OK);
        } catch (HuluCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // 3.	Delete the movie and show information using title.
    @DeleteMapping("/api/hulu/{title}")
    public ResponseEntity<?> deleteHuluByTitle(@PathVariable String title) {
        try{
            huluService.deleteHulu(title);
            return new ResponseEntity<>("Successfully deleted Hulu Show with title "+title, HttpStatus.OK);
        }
        catch (HuluCollectionException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // 4.	Retrieve all the movies and shows in database.
    @GetMapping("/api/hulu")
    public ResponseEntity<?> getAllHulu() {
        List<Hulu> allHulu = huluService.getAllHulu();
        return  new ResponseEntity<>(allHulu, allHulu.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }


    // 5.	Display the movie and show’s detail using title.

    @GetMapping("api/hulu/title/{title}")
    public ResponseEntity<?> getHuluDataByTitle(@PathVariable String title) {

        try {
            return new ResponseEntity<>(huluService.getHuluByTitle(title), HttpStatus.OK);
        } catch (HuluCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Get All the Hulu Data With the Id(Searches for the specified id and return the data with id).
    @GetMapping("/api/hulu/{id}")
    public ResponseEntity<?> getHuluById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(huluService.getHuluById(id), HttpStatus.OK);
        } catch (HuluCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
