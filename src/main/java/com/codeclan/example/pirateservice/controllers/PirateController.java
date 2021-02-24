package com.codeclan.example.pirateservice.controllers;

import com.codeclan.example.pirateservice.models.Pirate;
import com.codeclan.example.pirateservice.repositiores.PirateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PirateController {
    @Autowired
    PirateRepository pirateRepository;

    @GetMapping(value = "/pirates")
    // example queries localhost:8080/pirates
    public ResponseEntity <List<Pirate>> getAllPirates(){
        return new ResponseEntity<>(pirateRepository.findAll(), HttpStatus.OK) ;
    }

    @GetMapping(value = "/pirates/{id}")
    // example queries localhost:8080/pirates/1
    public ResponseEntity  getPirate(@PathVariable Long id) {
        return new ResponseEntity(pirateRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/pirates")
    // example queries localhost:8080/pirates
    public ResponseEntity postPirate(@RequestBody Pirate pirate){
        pirateRepository.save(pirate);
        return new ResponseEntity(pirate, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/pirates/{id}")
    // example queries localhost:8080/pirates/1
    public ResponseEntity deletePirate(@PathVariable Long id) {
        // Try to get the pirate with the id passed in
        Optional<Pirate> pirateToDelete = pirateRepository.findById(id);
        // Check if the pirate exists
        if (!pirateToDelete.isPresent()) {
            // if not, return NOT FOUND
            return new ResponseEntity(id, HttpStatus.NOT_FOUND);
        } else {
            // delete the pirate and return OK
            pirateRepository.delete(pirateToDelete.get());
            return new ResponseEntity(id, HttpStatus.OK);
        }
    }

    @PutMapping(value = "/pirates/{id}")
    // example queries localhost:8080/pirates/1
    public ResponseEntity putPirate(@RequestBody Pirate pirate, @PathVariable Long id)
    {
        Optional<Pirate> pirateToUpdateOptional = pirateRepository.findById(id);
        if (!pirateToUpdateOptional.isPresent()) {
            // if not, return NOT FOUND
            return new ResponseEntity(id, HttpStatus.NOT_FOUND);
        } else {
            Pirate pirateToUpdate = pirateToUpdateOptional.get();
            pirateToUpdate.setFirstName(pirate.getFirstName());
            pirateToUpdate.setLastName(pirate.getLastName());
            pirateToUpdate.setAge(pirate.getAge());
            pirateToUpdate.setShip(pirate.getShip());
            pirateRepository.save(pirateToUpdate);
            return new ResponseEntity(pirateToUpdate, HttpStatus.OK);
        }
    }
}
