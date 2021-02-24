package com.codeclan.example.pirateservice.repositiores;

import com.codeclan.example.pirateservice.models.Raid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RaidRepository extends JpaRepository<Raid, Long> {

 List<Raid> findRaidByLocation(String location);



}
