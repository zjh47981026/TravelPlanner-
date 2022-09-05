package com.laioffer.travelplanner.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "place")
@JsonDeserialize(builder = PlanPlace.Builder.class)
public class PlanPlace implements Serializable {

    @Id
    private Long id;

    private String name;

    private String type;

    public PlanPlace() {}

    private PlanPlace(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.type = builder.type;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getType() {
        return type;
    }


    public static class Builder {

        @JsonProperty("id")
        private Long id;

        @JsonProperty("name")
        private String name;


        @JsonProperty("type")
        private String type;


        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }


        public PlanPlace build() {
            return new PlanPlace(this);
        }
    }
}
