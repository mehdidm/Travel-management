package com.ms.hotelms.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="hotel_reservations")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "guest_name")
    private String guestName;

    @Column(name = "checkin_date")
    private LocalDate checkinDate;

    @Column(name = "hotel")
    private String hotel;



    @Column(name = "checkout_date")
    private LocalDate checkoutDate;

    @Column(name = "description")
    private String description;

    public ReservationEntity() {
    }

    public ReservationEntity(Long id, String guestName, LocalDate checkinDate, String hotel, LocalDate checkoutDate, String description) {
        this.id = id;
        this.guestName = guestName;
        this.checkinDate = checkinDate;
        this.hotel = hotel;
        this.checkoutDate = checkoutDate;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDate checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
