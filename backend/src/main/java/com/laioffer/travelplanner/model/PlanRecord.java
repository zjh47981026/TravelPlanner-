package com.laioffer.travelplanner.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "plan_record")
/*@AssociationOverrides({@AssociationOverride(name = "planRecord.user",
        joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "planRecord.plan",
                joinColumns = @JoinColumn(name = "plan_id"))}) */
public class PlanRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PlanRecordKey id;


    private boolean favorite;

    @ManyToOne
    @MapsId("user_id")
    private User user;

    @ManyToOne
    @MapsId("plan_id")
    private Plan plan;


    public PlanRecord() {}

    public PlanRecord (PlanRecordKey id, User user, Plan plan) {
        this.id = id;
        this.user = user;
        this.plan = plan;
    }

    public PlanRecordKey getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Plan getPlan() {
        return plan;
    }


    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
