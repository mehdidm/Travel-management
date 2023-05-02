package com.example.tripms.service.impl;

import com.example.tripms.service.TripService;
import com.example.tripms.entity.TripEntity;

import com.example.tripms.repository.TripRepository;
import com.example.tripms.service.TripService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;
    private final RestTemplate restTemplate ;
//    private final RabbitTemplate rabbitTemplate;

    public TripServiceImpl(TripRepository tripRepository, RestTemplate restTemplate, RabbitTemplate rabbitTemplate) {
        this.tripRepository = tripRepository;
        this.restTemplate = restTemplate;
//        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public List<TripEntity> findAllTrip() {
        return tripRepository.findAll();
    }

    @Override
    public Optional<TripEntity> findById(Long id) {
        return tripRepository.findById(id);
    }

    @Override
    public String saveTrip(TripEntity tripEntity) {
        TripEntity savedTrip = tripRepository.saveAndFlush(tripEntity);
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://fraud-ms/checkFraud/{cin}/{tripId}",
                String.class,
                savedTrip.getId()

        );

        if (response.getStatusCode() == HttpStatus.CREATED) {
            return ("Trip CANCELLED "+response.getBody()) ;
        }
        tripRepository.save(savedTrip);
        return ("Trip ACCEPTED") ;
    }

    @Override
    public TripEntity updateTrip(TripEntity tripEntity) {
        return tripRepository.save(tripEntity);
    }
    @RabbitListener(queues = "trips")
    @Override
    public void cancelTrip(long tripId) {
        TripEntity trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new IllegalArgumentException("Trip with ID " + tripId + " not found"));
        tripRepository.save(trip);
    }


    @Override
    public void deleteTrip(Long id) {
        log.info("Received cancellation request for trip with ID {}", id);
        TripEntity trip = tripRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Trip with ID " + id + " not found"));
        tripRepository.delete(trip);
    }
}
