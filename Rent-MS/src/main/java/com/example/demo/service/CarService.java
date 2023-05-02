package com.example.demo.service;

import com.example.demo.entity.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    public List<Car> findAvailableCars() {
        return carRepository.findByAvailableTrue();
    }

    public Optional<Car> findCarById(Long id) {
        return carRepository.findById(id);
    }

    public void rentCar(Long id) throws Exception {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (!optionalCar.isPresent()) {
            throw new Exception("Car not found");
        }
        Car car = optionalCar.get();
        if (!car.isAvailable()) {
            throw new Exception("Car already rented");
        }
        car.setAvailable(false);
        carRepository.save(car);
    }
}
