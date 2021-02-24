package com.codeclan.example.pirateservice.repositiores;

import com.codeclan.example.pirateservice.models.Pirate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PirateRepository extends JpaRepository<Pirate, Long> {

    List<Pirate> findByAgeGreaterThan(int age);


    List<Pirate> findByRaidsId(Long id);

}
