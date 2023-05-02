package com.example.demo.controller;

import com.example.demo.entity.Car;
import com.example.demo.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> findAllCars() {
        return carService.findAllCars();
    }

    @GetMapping("/available")
    public List<Car> findAvailableCars() {
        return carService.findAvailableCars();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> findCarById(@PathVariable Long id) {
        Optional<Car> optionalCar = carService.findCarById(id);
        return optionalCar.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/rent")
    public ResponseEntity<Void> rentCar(@PathVariable Long id) throws Exception {
        carService.rentCar(id);
        return ResponseEntity.ok().build();
    }
}
