package com.disertatie.rent.car.service.impl;

import com.disertatie.rent.car.entities.Car;
import com.disertatie.rent.car.exceptions.ExceptionExistingCar;
import com.disertatie.rent.car.model.CarModel;
import com.disertatie.rent.car.repository.CarRepository;
import com.disertatie.rent.car.service.CarService;
import com.disertatie.rent.car.transformers.Transformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "carService")
@Transactional
public class CarServiceImp implements CarService {

    @Resource(name = "carRepository")
    private CarRepository carRepository;

    @Resource(name = "transformer")
    private Transformer transformer;


    @Override
    public List<CarModel> getAllCars() {
        List<Car> carList = carRepository.findAll();
        List<CarModel> userModelList = new ArrayList<>();
        for (Car car : carList
        ) {
            userModelList.add(transformer.transformEntityToModel(car));
        }
        return userModelList;
    }

    @Override
    public void addCar(CarModel carModel) throws ExceptionExistingCar {
        Optional<Car> optionalUser = carRepository.findByVehicleIdentificationNumber(carModel.getVehicleIdentificationNumber());
        if (optionalUser.isPresent()) {
            throw new ExceptionExistingCar("Car with vehicle file identification number: " + carModel.getVehicleIdentificationNumber() + " already exists.");
        } else {
            carRepository.save(transformer.transformModelToEntity(carModel));
        }
    }
}
