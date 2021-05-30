package com.disertatie.rent.car.controller;

import com.disertatie.rent.car.dataGenerator.DataGenerator;
import com.disertatie.rent.car.exceptions.ExceptionExistingCar;
import com.disertatie.rent.car.exceptions.ExceptionNotFound;
import com.disertatie.rent.car.model.CarModel;
import com.disertatie.rent.car.model.CarQuizzModel;
import com.disertatie.rent.car.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/cars")
public class CarController {

    private final static Logger LOGGER = Logger.getLogger(CarController.class.getName());

    @Resource(name = "carService")
    private CarService carService;

    @Resource(name = "dataGenerator")
    private DataGenerator dataGenerator;


    @GetMapping(path = "/list", produces = "application/json")
    public ResponseEntity<List<CarModel>> getAllCars(@RequestParam(value = "startDate") String startDate, @RequestParam(value = "endDate") String endDate) {
        LOGGER.info("CarController : getAllCars()");
        LocalDate endDateTime = null;
        LocalDate startDateTime = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (!StringUtils.isEmpty(startDate) && !startDate.equals("null")) {
            startDateTime = LocalDate.parse(startDate, formatter);
        }
        if (!StringUtils.isEmpty(endDate) && !endDate.equals("null")) {
            endDateTime = LocalDate.parse(endDate, formatter);
        }
        return ResponseEntity.ok().body(carService.getAllCars(startDateTime, endDateTime));
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

    @GetMapping(path = "/availability")
    public ResponseEntity<Boolean> checkCarForAvailability(@RequestParam(value = "carId") Long carId, @RequestParam(value = "startDate") String startDate, @RequestParam(value = "endDate") String endDate){
        LOGGER.info("CarController : checkCarForAvailability()");
        LocalDate endDateTime = null;
        LocalDate startDateTime = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (!StringUtils.isEmpty(startDate) && !startDate.equals("null")) {
            startDateTime = LocalDate.parse(startDate, formatter);
        }
        if (!StringUtils.isEmpty(endDate) && !endDate.equals("null")) {
            endDateTime = LocalDate.parse(endDate, formatter);
        }
        return ResponseEntity.ok().body(carService.checkCarForAvailability(carId, startDateTime, endDateTime));
    }

    @PostMapping(path = "/quizz/{userId}", produces = "application/json")
    public ResponseEntity<List<CarModel>> getCarQuizz(@PathVariable(name = "userId") Long userId, @RequestBody CarQuizzModel carQuizzModel) throws ExceptionNotFound {
        LOGGER.info("CarController : getCarQuizz()");
        return ResponseEntity.ok().body(carService.getCarQuizz(userId, carQuizzModel));
    }

}
