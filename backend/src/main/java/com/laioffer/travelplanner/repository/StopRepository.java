package com.laioffer.travelplanner.repository;

import com.laioffer.travelplanner.model.Plan;
import com.laioffer.travelplanner.model.Stop;
import com.laioffer.travelplanner.model.StopKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StopRepository extends JpaRepository<Stop, StopKey> {
    List<Stop> findByPlan(Plan plan);
    Optional<Stop> findById(StopKey id);
}
