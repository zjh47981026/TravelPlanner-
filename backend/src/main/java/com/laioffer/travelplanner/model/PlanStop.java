package com.laioffer.travelplanner.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PlanStop {
    @JsonProperty("plan")
    private Plan plan;

    @JsonProperty("stops")
    private List<Stop> stops;

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }
}
