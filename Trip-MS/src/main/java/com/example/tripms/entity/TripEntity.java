package com.example.tripms.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="trips")
public class TripEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "destination")
    private String destination;

    @Column(name = "departure")
    private String departure;

    @Column(name = "price")
    private double price;

    @Column(name = "date")
    private LocalDate date;

    public TripEntity(Long id, String name, String description, String destination, String departure, double price, LocalDate date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.destination = destination;
        this.departure = departure;
        this.price = price;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
