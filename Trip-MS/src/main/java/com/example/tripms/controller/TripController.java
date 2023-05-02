package com.example.tripms.controller;

import com.example.tripms.entity.TripEntity;
import com.example.tripms.service.TripService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trips")
public class TripController {
    private final TripService tripService;

    public TripController(TripService tripService){
        this.tripService = tripService;
    }
    @GetMapping
    public List<TripEntity> findAllTrip(){
        return tripService.findAllTrip();
    }
    @GetMapping("/{id}")
    public Optional<TripEntity> findTripById(@PathVariable("id") Long id){
        return tripService.findById(id);
    }
    @PostMapping
    public String saveTrip(@RequestBody TripEntity tripEntity){
        return tripService.saveTrip(tripEntity);
    }

    @PutMapping
    public TripEntity updateTrip(@RequestBody TripEntity tripEntity){
        return tripService.updateTrip(tripEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable("id") Long id){
        tripService.deleteTrip(id);
    }

}
