package com.disertatie.rent.car.repository;

import com.disertatie.rent.car.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

@Repository(value = "carRepository")
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByVehicleIdentificationNumber(String vehicleIdentificationNumber);


    @Query(value = "SELECT * FROM CARS c LEFT JOIN RENT_DETAILS r ON c.car_id = r.car_id WHERE r.END_DATE < :startDate or r.START_DATE > :endDate", nativeQuery = true)
    List<Car> getAllNotBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
