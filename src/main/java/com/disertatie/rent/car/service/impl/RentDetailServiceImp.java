package com.disertatie.rent.car.service.impl;


import com.disertatie.rent.car.entities.RentDetail;
import com.disertatie.rent.car.exceptions.ExceptionAlreadyRent;
import com.disertatie.rent.car.model.OrderDetailModel;
import com.disertatie.rent.car.repository.RentDetailRepository;
import com.disertatie.rent.car.service.RentDetailService;
import com.disertatie.rent.car.transformers.Transformer;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service(value = "rentDetailService")
@Transactional
public class RentDetailServiceImp implements RentDetailService {

    @Resource(name = "rentDetailRepository")
    private RentDetailRepository rentDetailRepository;

    @Resource(name = "transformer")
    private Transformer transformer;

    @Override
    public Long addRentDetail(OrderDetailModel orderDetailModel) throws ExceptionAlreadyRent {
        RentDetail rentDetail = transformer.transformModelToEntityRentDetail(orderDetailModel);
        List<RentDetail> rentDetails = rentDetailRepository.findAllByDates(rentDetail.getStartDate(), rentDetail.getEndDate());
        if(rentDetails.size() > 0){
            throw new ExceptionAlreadyRent("The car is already rented for this period.");
        }else {
            return rentDetailRepository.save(rentDetail).getId();
        }

    }
}