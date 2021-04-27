package com.disertatie.rent.car.service.impl;

import com.disertatie.rent.car.entities.Car;
import com.disertatie.rent.car.exceptions.ExceptionExistingCar;
import com.disertatie.rent.car.exceptions.ExceptionNotFound;
import com.disertatie.rent.car.model.CarModel;
import com.disertatie.rent.car.repository.CarRepository;
import com.disertatie.rent.car.repository.RentDetailRepository;
import com.disertatie.rent.car.service.CarService;
import com.disertatie.rent.car.transformers.Transformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "carService")
@Transactional
public class CarServiceImp implements CarService {

    @Resource(name = "carRepository")
    private CarRepository carRepository;

    @Resource(name = "rentDetailRepository")
    private RentDetailRepository rentDetailRepository;

    @Resource(name = "transformer")
    private Transformer transformer;


    @Override
    public List<CarModel> getAllCars(LocalDate startDate, LocalDate endDate) {
        List<CarModel> carListModel = new ArrayList<>();
        List<Car> carList;
        if (startDate != null && endDate != null) {
            carList = carRepository.getAllNotBetweenDates(startDate, endDate);
        } else {
            carList = carRepository.findAll();
        }
        for (Car car : carList) {
            carListModel.add(transformer.transformEntityToModel(car));
        }
        return carListModel;
    }

    @Override
    public void addCar(CarModel carModel) throws ExceptionExistingCar {
        Optional<Car> optionalCar = carRepository.findByVehicleIdentificationNumber(carModel.getVehicleIdentificationNumber());
        if (optionalCar.isPresent()) {
            throw new ExceptionExistingCar("Car with vehicle file identification number: " + carModel.getVehicleIdentificationNumber() + " already exists.");
        } else {
            carRepository.save(transformer.transformModelToEntity(carModel));
        }
    }

    @Override
    public CarModel getCar(String vin) throws ExceptionNotFound {
        Optional<Car> optionalCar = carRepository.findByVehicleIdentificationNumber(vin);
        if (!optionalCar.isPresent()) {
            throw new ExceptionNotFound("Car with vehicle file identification number: " + vin + " does not exist.");
        } else {
            return transformer.transformEntityToModel(optionalCar.get());
        }
    }

    @Override
    public CarModel editCar(CarModel carModel) throws ExceptionNotFound {
        Optional<Car> optionalCar = carRepository.findByVehicleIdentificationNumber(carModel.getVehicleIdentificationNumber());
        if (!optionalCar.isPresent()) {
            throw new ExceptionNotFound("Car with vehicle file identification number: " + carModel.getVehicleIdentificationNumber() + " does not exist.");
        } else {
            Car car = optionalCar.get();
            carModel.setId(car.getId());
            return transformer.transformEntityToModel(carRepository.save(transformer.transformModelToEntity(carModel)));
        }
    }

    @Override
    public CarModel getCar(Long id) throws ExceptionNotFound {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (!optionalCar.isPresent()) {
            throw new ExceptionNotFound("Car with id: " + id + " does not exist.");
        } else {
            return transformer.transformEntityToModel(optionalCar.get());
        }
    }

    @Override
    public boolean checkCarForAvailability(Long carId, LocalDate startDateTime, LocalDate endDateTime) {
        return rentDetailRepository.checkCarForAvailability(carId, startDateTime, endDateTime).size() == 0;
    }
}
