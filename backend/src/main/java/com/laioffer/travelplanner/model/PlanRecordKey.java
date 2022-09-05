package com.laioffer.travelplanner.model;


import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PlanRecordKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private String user_id;
    private Long plan_id;

    public PlanRecordKey() {}

    public PlanRecordKey(String userId, Long planId) {
        this.user_id = userId;
        this.plan_id = planId;
    }

    public String getUser_id() {
        return user_id;
    }

    public PlanRecordKey setUser_id(String userId) {
        this.user_id = userId;
        return this;
    }

    public Long getPlan_id() {
        return plan_id;
    }


    public PlanRecordKey setPlan_id(Long planId) {
        this.plan_id = planId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanRecordKey that = (PlanRecordKey) o;
        return Objects.equals(user_id, that.user_id) && Objects.equals(plan_id, that.plan_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, plan_id);
    }
}
