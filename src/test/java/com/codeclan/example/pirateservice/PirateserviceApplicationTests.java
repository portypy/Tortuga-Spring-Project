package com.codeclan.example.pirateservice;


import com.codeclan.example.pirateservice.models.Pirate;
import com.codeclan.example.pirateservice.models.Raid;
import com.codeclan.example.pirateservice.models.Ship;
import com.codeclan.example.pirateservice.repositiores.PirateRepository;
import com.codeclan.example.pirateservice.repositiores.RaidRepository;
import com.codeclan.example.pirateservice.repositiores.ShipRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PirateserviceApplicationTests {
    @Autowired
    PirateRepository pirateRepository;

    @Autowired
    ShipRepository shipRepository;

    @Autowired
    RaidRepository raidRepository;

    @Test
    public void contextLoads(){
    }

    @Test
    public void createPirateAndShip(){
        Ship ship = new Ship("The Flying Dutchman");
        shipRepository.save(ship);

        Pirate pirate1 = new Pirate("Jack", "Sparrow", 32, ship);
        pirateRepository.save(pirate1);
    }
    @Test
    public void addPiratesAndRaids(){
        Ship ship = new Ship("The Flying Dutchman");
        shipRepository.save(ship);

        Pirate pirate = new Pirate("Jack", "Sparrow", 32, ship);
        pirateRepository.save(pirate);

        Raid raid = new Raid("Tortuga", 100);
        raidRepository.save(raid);

        raid.addPirate(pirate);
        raidRepository.save(raid);

    }

    @Test
    public void canFindPiratesOver30(){
        List<Pirate> found = pirateRepository.findByAgeGreaterThan(30);
        assertEquals(7, found.size());
    }

    @Test
    public void canFindRaidByLocation(){
        List<Raid> found = raidRepository.findRaidByLocation("Tortuga");
        assertEquals("Tortuga", found.get(0).getLocation());
    }

    @Test
    public void canfindPirateByRaidId(){
        List<Pirate> foundPirates = pirateRepository.findByRaidsId(1L);
        assertEquals(1, foundPirates.size());
        assertEquals("Jack", foundPirates.get(0).getFirstName());
    }

    @Test
    public void canFindShipsWithPiratesFirstName(){
        List<Ship> foundShip = shipRepository.findByPiratesFirstName("Maggie");
        assertEquals("The Flying Dustman", foundShip.get(0).getName());
    }
}