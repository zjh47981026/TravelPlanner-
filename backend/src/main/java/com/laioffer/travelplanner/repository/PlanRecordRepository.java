package com.laioffer.travelplanner.repository;

import com.laioffer.travelplanner.model.PlanRecord;
import com.laioffer.travelplanner.model.PlanRecordKey;
import com.laioffer.travelplanner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanRecordRepository extends JpaRepository<PlanRecord, PlanRecordKey> {
    List<PlanRecord> findByUser(User user);

    Optional<PlanRecord> findById(PlanRecordKey id);
}
