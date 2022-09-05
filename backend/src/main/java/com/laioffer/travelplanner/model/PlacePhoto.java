package com.laioffer.travelplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = PlacePhoto.Builder.class)
public class PlacePhoto {
    @JsonProperty("photo_reference")
    private final String photoRef;

    private PlacePhoto(Builder builder) {
        this.photoRef = builder.photoRef;
    }

    public String getPhotoRef() {return photoRef;}

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Builder{
        @JsonProperty("photo_reference")
        private String photoRef;

        public Builder photoRef(String photoRef) {
            this.photoRef = photoRef;
            return this;
        }

        public PlacePhoto build() {return new PlacePhoto(this);}
    }
}
