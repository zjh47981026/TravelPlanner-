package com.laioffer.travelplanner.repository;

import com.laioffer.travelplanner.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    Plan findById(long id);
    List<Plan> findAllByAuthor(String name);
    List<Plan> findByAuthorAndAndStartDateAndAndEndDate(String author, LocalDate startDate, LocalDate endDate);
}
