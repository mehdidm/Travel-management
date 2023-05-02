package com.example.tripms.service;

import com.example.tripms.entity.TripEntity;

import java.util.List;
import java.util.Optional;

public interface TripService {

    List<TripEntity> findAllTrip();

    Optional<TripEntity> findById(Long id);
    String saveTrip(TripEntity reservationEntity);
    TripEntity updateTrip(TripEntity reservationEntity);
    void cancelTrip (long Message);
    void deleteTrip (Long id);


}
