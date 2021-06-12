package com.disertatie.rent.car.service;

import com.disertatie.rent.car.entities.SimilarityValue;

import java.util.List;

public interface SimilarityValueService {
    void save(SimilarityValue similarityValue);

    void deleteAll();

    List<SimilarityValue> getAll();

    List<SimilarityValue> getByCarId(long carId);

    double getRatingByUserIdAndCarId(long userId, long carId);
}
