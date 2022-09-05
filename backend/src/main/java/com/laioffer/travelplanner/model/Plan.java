package com.laioffer.travelplanner.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "plan")
@JsonDeserialize(builder = Plan.Builder.class)
public class Plan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("start_from")
    private LocalDate startDate;

    @JsonProperty("end_at")
    private LocalDate endDate;

    @JsonProperty("duration")
    private int duration;

    @JsonProperty("author")
    private String author;

    public Plan() {}

    private Plan (Builder builder) {
        this.id = builder.id;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.duration = builder.duration;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getDuration() {
        return duration;
    }

    public String getAuthor() {
        return author;
    }

    public Plan setAuthor(String author) {
        this.author = author;
        return this;
    }

    public static class Builder {
        @JsonProperty("id")
        private Long id;

        @JsonProperty("start_from")
        private LocalDate startDate;

        @JsonProperty("end_at")
        private LocalDate endDate;

        @JsonProperty("duration")
        private int duration;

        @JsonProperty("author")
        private String author;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }
        public Plan build() {
            return new Plan(this);
        }
    }

}
