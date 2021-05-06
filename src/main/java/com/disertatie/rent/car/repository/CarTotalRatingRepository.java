package com.disertatie.rent.car.repository;

import com.disertatie.rent.car.entities.CarTotalRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "carTotalRatingRepository")
public interface CarTotalRatingRepository extends JpaRepository<CarTotalRating, Long> {

    @Query(value = "SELECT * FROM CAR_TOTAL_RATINGS c WHERE c.car_id = :carId", nativeQuery = true)
    Optional<CarTotalRating> getByCarId(Long carId);
}
