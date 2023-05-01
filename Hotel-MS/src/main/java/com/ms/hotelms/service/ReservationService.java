package com.ms.hotelms.service;

import com.ms.hotelms.entity.ReservationEntity;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    List<ReservationEntity> findAllReservation();

    Optional<ReservationEntity> findById(Long id);
    String saveReservation(ReservationEntity reservationEntity);
    ReservationEntity updateReservation(ReservationEntity reservationEntity);
    void cancelReservation (long Message);
    void deleteReservation (Long id);


}
