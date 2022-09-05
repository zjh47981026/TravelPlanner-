package com.laioffer.travelplanner.repository;

import com.laioffer.travelplanner.model.Plan;
import com.laioffer.travelplanner.model.PlanPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanPlaceRepository extends JpaRepository<PlanPlace, Long> {
    PlanPlace findById(long id);
}
