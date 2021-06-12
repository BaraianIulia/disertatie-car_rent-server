package com.disertatie.rent.car.repository;

import com.disertatie.rent.car.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository(value = "carRepository")
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByVehicleIdentificationNumber(String vehicleIdentificationNumber);


    @Query(value = "SELECT * FROM CARS c LEFT JOIN RENT_DETAILS r ON c.car_id = r.car_id WHERE r.END_DATE < :startDate or r.START_DATE > :endDate", nativeQuery = true)
    List<Car> getAllNotBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT * FROM CARS c WHERE c.brand IN (:brand) AND c.fabrication_year LIKE :fabricationYear AND price_per_day LIKE :pricePerDay AND horse_power LIKE :horsePower AND c.seats LIKE :seats AND c.color IN (:color) ", nativeQuery = true)
    List<Car> carQuizz(List<String> brand, Integer fabricationYear, Integer pricePerDay, Integer horsePower, Integer seats, List<String> color);

    @Query(value = "SELECT * FROM CARS c LEFT JOIN CAR_TOTAL_RATINGS r ON c.car_id = r.car_id WHERE r.rating > 3 ORDER BY r.rating DESC Limit 0, 3", nativeQuery = true)
    List<Car> findTop3();
}
