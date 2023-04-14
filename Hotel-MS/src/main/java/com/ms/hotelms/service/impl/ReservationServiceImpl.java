package com.ms.hotelms.service.impl;

import com.ms.hotelms.entity.ReservationEntity;
import com.ms.hotelms.repository.ReservationRepository;
import com.ms.hotelms.service.ReservationService;
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
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final RestTemplate restTemplate ;
//    private final RabbitTemplate rabbitTemplate;

    public ReservationServiceImpl(ReservationRepository reservationRepository, RestTemplate restTemplate, RabbitTemplate rabbitTemplate) {
        this.reservationRepository = reservationRepository;
        this.restTemplate = restTemplate;
//        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public List<ReservationEntity> findAllReservation() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<ReservationEntity> findById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public String saveReservation(ReservationEntity reservationEntity) {
        ReservationEntity savedReservation = reservationRepository.saveAndFlush(reservationEntity);
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://FRAUD-MS/checkFraud/{cin}/{reservationId}",
                String.class,
                savedReservation.getCin(),
                savedReservation.getId()

        );

        if (response.getStatusCode() == HttpStatus.CREATED) {
            return ("Reservation CANCELLED "+response.getBody()) ;
        }
        savedReservation.setStatus("ACCEPTED");
        reservationRepository.save(savedReservation);
        return ("Reservation ACCEPTED") ;
    }

    @Override
    public ReservationEntity updateReservation(ReservationEntity reservationEntity) {
        return reservationRepository.save(reservationEntity);
    }
    @RabbitListener(queues = "reservations")
    @Override
    public void cancelReservation(long reservationId) {
        ReservationEntity reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation with ID " + reservationId + " not found"));
        reservation.setStatus("CANCELLED");
        reservationRepository.save(reservation);
    }


    @Override
    public void deleteReservation(Long id) {
        log.info("Received cancellation request for reservation with ID {}", id);
        ReservationEntity reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation with ID " + id + " not found"));
        reservationRepository.delete(reservation);
    }
}
