package com.disertatie.rent.car.controller;

import com.disertatie.rent.car.exceptions.ExceptionExistingCar;
import com.disertatie.rent.car.exceptions.ExceptionNotFound;
import com.disertatie.rent.car.model.CarModel;
import com.disertatie.rent.car.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/cars")
public class CarController {

    private final static Logger LOGGER = Logger.getLogger(CarController.class.getName());

    @Resource(name = "carService")
    private CarService carService;

    @GetMapping(path = "/list", produces = "application/json")
    public ResponseEntity<List<CarModel>> getAllCars() {
        LOGGER.info("CarController : getAllCars()");
        return ResponseEntity.ok().body(carService.getAllCars());
    }

    @PostMapping(path = "/add")
    public ResponseEntity addCar(@RequestBody CarModel carModel) throws ExceptionExistingCar {
        LOGGER.info("CarController : add " + carModel.getVehicleIdentificationNumber());
        carService.addCar(carModel);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/details/{vin}", produces = "application/json")
    public ResponseEntity<CarModel> getCar(@PathVariable(name = "vin") String vin) throws ExceptionNotFound {
        LOGGER.info("CarController : getCar()" + vin);
        return ResponseEntity.ok().body(carService.getCar(vin));
    }

    @PostMapping(path = "/edit")
    public CarModel editCar(@RequestBody CarModel carModel) throws ExceptionNotFound {
        LOGGER.info("CarController : edit " + carModel.getVehicleIdentificationNumber());
        return carService.editCar(carModel);
    }
}
