package com.ms.hotelms.service.impl;

import com.ms.hotelms.entity.ReservationEntity;
import com.ms.hotelms.repository.ReservationRepository;
import com.ms.hotelms.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
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
        return null;
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
