package com.disertatie.rent.car.service;

import com.disertatie.rent.car.entities.RentDetail;
import com.disertatie.rent.car.exceptions.ExceptionAlreadyRent;
import com.disertatie.rent.car.model.OrderDetailModel;

public interface RentDetailService {

    Long addRentDetail(OrderDetailModel orderDetailModel) throws ExceptionAlreadyRent;
}
