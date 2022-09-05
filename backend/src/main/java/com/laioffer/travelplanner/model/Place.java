package com.laioffer.travelplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = Place.Builder.class)
public class Place {


    @JsonProperty("name")
    private final String name;

    @JsonProperty("formatted_address")
    private final String address;

    @JsonProperty("photos")
    private final PlacePhoto[] photos;

    @JsonProperty("rating")
    private final String rating;


    @JsonProperty("photo_links")
    private String[] photoLinks;



    private Place(Builder builder) {
        this.name = builder.name;
        this.address = builder.address;
        this.photos = builder.photos;
        this.rating = builder.rating;
    }

    public PlacePhoto[] getPhotos() {return photos;}


    public String getAddress() {
        return address;
    }

    public String getName() {return name;}

    public String getRating() {return rating;}

    public void setPhotoLinks(String[] photoLinks) {
        this.photoLinks = photoLinks;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Builder{

        @JsonProperty("name")
        private String name;

        @JsonProperty("formatted_address")
        private String address;

        @JsonProperty("photos")
        private PlacePhoto[] photos;

        @JsonProperty("rating")
        private String rating;

        public Builder name (String name) {
            this.name = name;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder photos(PlacePhoto[] photos) {
            this.photos = photos;
            return this;
        }

        public Builder rating(String rating) {
            this.rating = rating;
            return this;
        }

        public Place build() {return new Place(this);}

    }
}
