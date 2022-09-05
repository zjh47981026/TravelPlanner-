package com.laioffer.travelplanner.service;

import com.laioffer.travelplanner.exception.InvalidStopScheduleException;
import com.laioffer.travelplanner.exception.PlanNotExistException;
import com.laioffer.travelplanner.exception.StopAlreadyExistException;
import com.laioffer.travelplanner.exception.StopNotExistException;
import com.laioffer.travelplanner.model.*;
import com.laioffer.travelplanner.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PlanService {
    private PlanRepository planRepository;
    private PlanRecordRepository planRecordRepository;
    private StopRepository stopRepository;
    private PlanPlaceRepository planPlaceRepository;

    @Autowired
    public PlanService(PlanRepository planRepository,
                        PlanRecordRepository planRecordRepository,
                       StopRepository stopRepository,
                       PlanPlaceRepository planPlaceRepository) {
        this.planRepository = planRepository;
        this.planRecordRepository = planRecordRepository;
        this.stopRepository = stopRepository;
        this.planPlaceRepository = planPlaceRepository;
    }

    public List<Plan> listById(String username) {
        List<PlanRecord> records = planRecordRepository.findByUser(new User.Builder().setUsername(username).build());
        List<Plan> plans = new ArrayList<>();
        for (PlanRecord record : records) {
            Optional<Plan> p = planRepository.findById(record.getPlan().getId());
            if (p.isPresent()) {
                plans.add(p.get());
            }
        }
        return plans;
    }

    public List<Plan> listByUser(String username) {
        return planRepository.findAllByAuthor(username);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void addPlan(String username, Plan plan) {
        planRepository.save(plan);
        List<Plan> plans = planRepository.findByAuthorAndAndStartDateAndAndEndDate(username, plan.getStartDate(),
                plan.getEndDate());
        planRecordRepository.save(new PlanRecord(new PlanRecordKey(username, plans.get(0).getId()),
                new User.Builder().setUsername(username).build(), plans.get(0)));
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void addStop(long planId, Stop stop) throws StopAlreadyExistException, InvalidStopScheduleException {
        Optional<Stop> s = stopRepository.findById(new StopKey(planId, stop.getPlace().getId()));
        if (s.isPresent()) {
            throw new StopAlreadyExistException("Stop Already Exist");
        }
        List<Stop> stops = getStops(planId);
        for (Stop planStop : stops) {
            int existedStartTime = Integer.parseInt(planStop.getStartTime());
            int existedEndTime = Integer.parseInt(planStop.getEndTime());
            int curStartTime = Integer.parseInt(stop.getStartTime());
            if (planStop.getDay() == stop.getDay() && curStartTime >= existedStartTime &&
                    curStartTime <= existedEndTime) {
                throw new InvalidStopScheduleException("Schedule conflict!");
            }
        }
        Optional<PlanPlace> place = planPlaceRepository.findById(stop.getPlace().getId());
        if (!place.isPresent()) {
            planPlaceRepository.save(stop.getPlace());
        }
        stop.setPlan(new Plan.Builder().setId(planId).build());
        stop.setId(new StopKey(planId, stop.getPlace().getId()));
        stopRepository.save(stop);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteStop(Long planId, Long placeId) throws StopNotExistException {
        Optional<Stop> stop = stopRepository.findById(new StopKey(planId, placeId));
        if (!stop.isPresent()) {
            throw new StopNotExistException("Stop doesn't exist!");
        }
        stopRepository.deleteById(new StopKey(planId, placeId));
    }

    public List<Stop> getStops(Long planId) {
        List<Stop> stops = stopRepository.findByPlan(new Plan.Builder().setId(planId).build());
        for (Stop stop : stops) {
            Optional<PlanPlace> p = planPlaceRepository.findById(stop.getPlace().getId());
            if (p.isPresent()) {
                stop.setPlace(p.get());
            }
        }
        Collections.sort(stops, new Comparator<Stop>() {
            @Override
            public int compare(Stop o1, Stop o2) {
                if (o1.getDay() < o2.getDay()) {
                    return -1;
                } else if (o1.getDay() > o2.getDay()) {
                    return 1;
                }
                if (Integer.parseInt(o1.getStartTime()) < Integer.parseInt(o2.getStartTime())) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        return stops;
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deletePlan(Long planId, String username) throws PlanNotExistException {
        Optional<PlanRecord> record =  planRecordRepository.findById(new PlanRecordKey(username, planId));
        if (record.isPresent()) {
            planRecordRepository.deleteById(new PlanRecordKey(username, planId));
        }

        Optional<Plan> plan = planRepository.findById(planId);
        if (!plan.isPresent()) {
            throw new PlanNotExistException("Plan doesn't exist");
        }

        List<Stop> stops = stopRepository.findByPlan(new Plan.Builder().setId(planId).build());

        for (Stop stop : stops) {
            stopRepository.deleteById(stop.getId());
            planPlaceRepository.deleteById(stop.getPlace().getId());
        }
        planRepository.deleteById(planId);
    }
}
