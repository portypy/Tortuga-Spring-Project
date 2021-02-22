package com.codeclan.example.pirateservice.repositiores;

import com.codeclan.example.pirateservice.models.Raid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaidRepository extends JpaRepository<Raid, Long> {
}
