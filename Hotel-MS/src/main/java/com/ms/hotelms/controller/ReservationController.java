package com.ms.hotelms.controller;

import com.ms.hotelms.entity.ReservationEntity;
import com.ms.hotelms.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }
    @GetMapping
    public List<ReservationEntity> findAllReservation(){
        return reservationService.findAllReservation();
    }
    @GetMapping("/{id}")
    public Optional<ReservationEntity> findReservationById(@PathVariable("id") Long id){
        return reservationService.findById(id);
    }
    @PostMapping
    public String saveReservation(@RequestBody ReservationEntity reservationEntity){
        return reservationService.saveReservation(reservationEntity);
    }

    @PutMapping
    public ReservationEntity updateReservation(@RequestBody ReservationEntity reservationEntity){
        return reservationService.updateReservation(reservationEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable("id") Long id){
        reservationService.deleteReservation(id);
    }

}
