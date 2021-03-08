package com.disertatie.rent.car.repository;

import com.disertatie.rent.car.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "carRepository")
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByVehicleIdentificationNumber(String vehicleIdentificationNumber);
}
