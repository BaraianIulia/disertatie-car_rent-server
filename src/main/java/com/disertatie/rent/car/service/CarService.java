package com.disertatie.rent.car.service;

import com.disertatie.rent.car.entities.Car;
import com.disertatie.rent.car.exceptions.ExceptionExistingCar;
import com.disertatie.rent.car.exceptions.ExceptionNotFound;
import com.disertatie.rent.car.model.CarModel;
import com.disertatie.rent.car.model.CarQuizzModel;

import java.time.LocalDate;
import java.util.List;

public interface CarService {

    List<CarModel> getAllCars(LocalDate startDate, LocalDate endDate);

    void addCar(CarModel carModel) throws ExceptionExistingCar;

    CarModel getCar(String vin) throws ExceptionNotFound;

    CarModel editCar(CarModel carModel) throws ExceptionNotFound;

    CarModel getCar(Long id) throws ExceptionNotFound;

    boolean checkCarForAvailability(Long carId, LocalDate startDateTime, LocalDate endDateTime);

    List<CarModel> getCarQuizz(Long userId, CarQuizzModel carQuizzModel) throws ExceptionNotFound;

    Car getCarEntity(String vehicleIdentificationNumber) throws ExceptionNotFound;

    void updateCar(Car car) throws ExceptionNotFound;

    List<CarModel> getCarTopCar();
}
