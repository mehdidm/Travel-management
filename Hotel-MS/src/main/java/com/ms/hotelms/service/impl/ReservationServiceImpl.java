package com.ms.hotelms.service.impl;

import com.ms.hotelms.Response.FraudCheckResponse;
import com.ms.hotelms.entity.ReservationEntity;
import com.ms.hotelms.repository.ReservationRepository;
import com.ms.hotelms.service.ReservationService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final RestTemplate restTemplate ;

    public ReservationServiceImpl(ReservationRepository reservationRepository, RestTemplate restTemplate) {
        this.reservationRepository = reservationRepository;
        this.restTemplate = restTemplate;
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
    public ReservationEntity saveReservation(ReservationEntity reservationEntity) {
        ReservationEntity resrvation = reservationRepository.saveAndFlush(reservationEntity);
        FraudCheckResponse fraudChckResponse = restTemplate.getForObject(
                "http://FRAUD-MS/api/v1/fraud-check{clientId}",
                FraudCheckResponse.class,
                resrvation.getId()

        );
        if (fraudChckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
        return reservationEntity  ;
    }

    @Override
    public ReservationEntity updateReservation(ReservationEntity reservationEntity) {
        return reservationRepository.save(reservationEntity);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

}
