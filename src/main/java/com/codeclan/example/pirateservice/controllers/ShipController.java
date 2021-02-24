package com.codeclan.example.pirateservice.controllers;

import com.codeclan.example.pirateservice.models.Ship;
import com.codeclan.example.pirateservice.repositiores.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShipController {
    @Autowired
    ShipRepository shipRepository;

    @GetMapping(value = "/ships")
    // example queries localhost:8080/ships
    public ResponseEntity<List<Ship>> getAllShips(){
        return new ResponseEntity<>(shipRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/ships/{id}")
    // example queries localhost:8080/ships/1
    public ResponseEntity getShip(@PathVariable Long id){
        return new ResponseEntity(shipRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/ships")
    // example queries localhost:8080/ships
    public ResponseEntity postShip(@RequestBody Ship ship){
        shipRepository.save(ship);
        return new ResponseEntity(ship , HttpStatus.CREATED);
    }

    @GetMapping(value="/ships/pirates")
    // example queries localhost:8080/ships/pirates?named=Maggie
    public ResponseEntity<List<Ship>> findShipsThatHavePiratesNamedQueryString(@RequestParam(name = "named") String name){
        return new ResponseEntity<>(shipRepository.findByPiratesFirstName(name), HttpStatus.OK);
    }
}
