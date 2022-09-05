package com.laioffer.travelplanner.service;

import com.laioffer.travelplanner.model.Plan;
import com.laioffer.travelplanner.model.PlanPlace;
import com.laioffer.travelplanner.repository.PlanPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlanPlaceService {
    private PlanPlaceRepository planPlaceRepository;

    @Autowired
    public PlanPlaceService(PlanPlaceRepository planPlaceRepository) {
        this.planPlaceRepository = planPlaceRepository;
    }


}
