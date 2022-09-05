package com.laioffer.travelplanner.model;



import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StopKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long plan_id;
    private Long place_id;

    public StopKey() {}

    public StopKey(Long planId, Long placeId) {
        this.plan_id = planId;
        this.place_id = placeId;
    }

    public StopKey setPlan_id(Long planId) {
        this.plan_id = planId;
        return this;
    }

    public Long getPlan_id() {
        return plan_id;
    }

    public StopKey setPlace_id(Long placeId) {
        this.place_id = placeId;
        return this;
    }

    public Long getPlace_id() {return place_id;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StopKey stopKey = (StopKey) o;
        return Objects.equals(plan_id, stopKey.plan_id) && Objects.equals(place_id, stopKey.place_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plan_id, place_id);
    }
}

