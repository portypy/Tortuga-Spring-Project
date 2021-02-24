package com.codeclan.example.pirateservice.controllers;

import com.codeclan.example.pirateservice.models.Raid;
import com.codeclan.example.pirateservice.models.Ship;
import com.codeclan.example.pirateservice.repositiores.RaidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RaidController {
    @Autowired
    RaidRepository raidRepository;

//    @GetMapping(value = "/raids")
//    public ResponseEntity<List<Raid>> getAllRaids(){
//        return new ResponseEntity<>(raidRepository.findAll(), HttpStatus.OK);
//    }

    @GetMapping(value = "/raids/{id}")
    // example queries localhost:8080/
    public ResponseEntity getRaid(@PathVariable Long id){
        return new ResponseEntity(raidRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/raids")
    // example queries localhost:8080/
    public ResponseEntity postRaid(@RequestBody Raid raid){
        raidRepository.save(raid);
        return new ResponseEntity(raid, HttpStatus.CREATED);
    }

    @GetMapping(value="/raids")
    // example queries localhost:8080/
    public ResponseEntity<List<Raid>> findRaidsFilteredByLocation(
            @RequestParam (name="location", required = false) String location,
            @RequestParam (name="lootieSize", required=false) Integer lootieSize) {
        if (location != null) {
            return new ResponseEntity<>(raidRepository.findRaidByLocation(location), HttpStatus.OK);
        }
        if (lootieSize != null) {
            return new ResponseEntity<>(raidRepository.findByLootGreaterThan(lootieSize), HttpStatus.OK);
        }
        return new ResponseEntity<>(raidRepository.findAll(), HttpStatus.OK);
    }


}
