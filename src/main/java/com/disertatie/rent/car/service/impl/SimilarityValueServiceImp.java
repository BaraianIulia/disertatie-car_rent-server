package com.disertatie.rent.car.service.impl;

import com.disertatie.rent.car.entities.SimilarityValue;
import com.disertatie.rent.car.repository.SimilarityValueRepository;
import com.disertatie.rent.car.service.SimilarityValueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service(value = "similarityValueService")
@Transactional
public class SimilarityValueServiceImp implements SimilarityValueService {

    @Resource(name = "similarityValueRepository")
    private SimilarityValueRepository similarityValueRepository;

    @Override
    public void save(SimilarityValue similarityValue) {
        similarityValueRepository.save(similarityValue);
    }

    @Override
    public void deleteAll() {
        similarityValueRepository.deleteAll();
    }

    @Override
    public List<SimilarityValue> getAll() {
        return similarityValueRepository.findAll();
    }

    @Override
    public double getRatingByUserIdAndCarId(long userId, long carId) {
        Optional<SimilarityValue> similarityValueOptional = similarityValueRepository.getBySimilarityForUserAndCarId(userId, carId);
        if (similarityValueOptional.isPresent()) {
            return similarityValueOptional.get().getValue();
        }
        return 1;

    }
}
