package com.laioffer.travelplanner.controller;

import com.laioffer.travelplanner.model.Place;
import com.laioffer.travelplanner.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlaceController {
    private PlaceService placeService;


    @Autowired
    public PlaceController(PlaceService placeService) {this.placeService = placeService;}


    @GetMapping(value = "/places")
    public List<Place> searchPlaces() {
        return placeService.searchPlace();
    }

}
