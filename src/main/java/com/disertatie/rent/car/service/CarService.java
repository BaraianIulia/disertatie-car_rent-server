package com.disertatie.rent.car.service;

import com.disertatie.rent.car.exceptions.ExceptionExistingCar;
import com.disertatie.rent.car.model.CarModel;

import java.util.List;

public interface CarService {

    List<CarModel> getAllCars();

    void addCar(CarModel carModel) throws ExceptionExistingCar;
}
