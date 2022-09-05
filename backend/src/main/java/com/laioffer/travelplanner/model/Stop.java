package com.laioffer.travelplanner.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stop")
public class Stop implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private StopKey id;

    @JsonProperty("start_time")
    private String startTime;

    @JsonProperty("end_time")
    private String endTime;

    @JsonProperty("day")
    private int day;

    @ManyToOne
    @MapsId("plan_id")
    private Plan plan;

    @ManyToOne
    @MapsId("place_id")
    @JsonProperty("place")
    private PlanPlace place;

    public Stop() {}

    public Stop(StopKey id, String startTime, String endTime, int day, Plan plan, PlanPlace place) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
        this.plan = plan;
        this.place = place;
    }

    public void setId(StopKey id) {
        this.id = id;
    }

    public StopKey getId() {
        return id;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Plan getPlan() {return plan;}

    public PlanPlace getPlace() {
        return place;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public void setPlace(PlanPlace place) {
        this.place = place;
    }

    public int getDay() {
        return day;
    }
}
