package com.disertatie.rent.car.repository;

import com.disertatie.rent.car.entities.SimilarityValue;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "similarityValueRepository")
public interface SimilarityValueRepository extends JpaRepository<SimilarityValue, Long> {

    Optional<SimilarityValue> getBySimilarityForUserAndCarId(long similarityForUser, long carId);

    List<SimilarityValue> getAllByCarId(long carId);
}