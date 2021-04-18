package com.disertatie.rent.car.service;

import com.disertatie.rent.car.exceptions.ExceptionAlreadyRent;
import com.disertatie.rent.car.exceptions.ExceptionNotFound;
import com.disertatie.rent.car.model.OrderDetailModel;

public interface ReceiptService {

     void addReceipt(OrderDetailModel orderDetailModel) throws ExceptionAlreadyRent, ExceptionNotFound;
}
