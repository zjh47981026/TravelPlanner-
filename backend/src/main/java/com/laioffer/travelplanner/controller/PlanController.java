package com.laioffer.travelplanner.controller;


import com.laioffer.travelplanner.exception.InvalidStopScheduleException;
import com.laioffer.travelplanner.model.Plan;
import com.laioffer.travelplanner.model.Stop;
import com.laioffer.travelplanner.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class PlanController {
    private PlanService planService;

    @Autowired
    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping(value = "/plans")
    public List<Plan> getPlan(Principal principal) {
        return planService.listByUser(principal.getName());
    }

    @GetMapping(value = "/plans/{planId}/stops")
    public List<Stop> getPlanStops(@PathVariable Long planId) {
        return planService.getStops(planId);
    }

    @PostMapping("/plans/add")
    public void addPlan(@RequestBody Plan plan,
                        Principal principal) {
        plan.setAuthor(principal.getName());
        planService.addPlan(principal.getName(), plan);
    }

    @PostMapping("/plans/{planId}/stop")
    public void addStop(@PathVariable Long planId, @RequestBody Stop stop)  throws InvalidStopScheduleException {
        int startTime = Integer.parseInt(stop.getStartTime());
        int endTime = Integer.parseInt(stop.getEndTime());
        if (startTime >= endTime) {
            throw new InvalidStopScheduleException("Invalid Stop!");
        }
        planService.addStop(planId, stop);
    }

    @DeleteMapping("/plans/{planId}/stop/{placeId}/edit")
    public void deleteStop(@PathVariable Long planId, @PathVariable Long placeId) {
        planService.deleteStop(planId, placeId);
    }


    @DeleteMapping("/plans/{planId}")
    public void deleteStay(@PathVariable Long planId, Principal principal) {
        planService.deletePlan(planId, principal.getName());
    }


}
