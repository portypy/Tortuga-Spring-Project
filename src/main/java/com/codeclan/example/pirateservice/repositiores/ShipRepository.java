package com.codeclan.example.pirateservice.repositiores;

import com.codeclan.example.pirateservice.models.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ShipRepository extends JpaRepository<Ship, Long> {

    List<Ship> findByPiratesFirstName(String firstName);

}
