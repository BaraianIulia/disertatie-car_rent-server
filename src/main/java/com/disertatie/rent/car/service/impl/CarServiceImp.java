package com.disertatie.rent.car.service.impl;

import com.disertatie.rent.car.entities.Car;
import com.disertatie.rent.car.exceptions.ExceptionExistingCar;
import com.disertatie.rent.car.exceptions.ExceptionNotFound;
import com.disertatie.rent.car.model.CarModel;
import com.disertatie.rent.car.model.CarQuizzModel;
import com.disertatie.rent.car.recommender.Recommender;
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
import java.util.stream.Collectors;

@Service(value = "carService")
@Transactional
public class CarServiceImp implements CarService {

    @Resource(name = "carRepository")
    private CarRepository carRepository;

    @Resource(name = "rentDetailRepository")
    private RentDetailRepository rentDetailRepository;

    @Resource(name = "transformer")
    private Transformer transformer;

    @Resource(name = "recommender")
    private Recommender recommender;


    @Override
    public List<CarModel> getAllCars(LocalDate startDate, LocalDate endDate) {
        List<CarModel> carListModel = new ArrayList<>();
        List<Car> carList = new ArrayList<>();
        if (startDate != null && endDate != null) {
           carList = carRepository.getAllNotBetweenDates(startDate, endDate);
        }else{
            carList =carRepository.findAll();
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

    @Override
    public List<CarModel> getCarQuizz(Long userId, CarQuizzModel carQuizzModel) throws ExceptionNotFound {
//        List<CarModel> carListModel = new ArrayList<>();
//        List<Car> carList = carRepository.findAll();
//        if (carQuizzModel.getBrand().size() > 0) {
//            carList = carList.stream()
//                    .filter(car -> carQuizzModel.getBrand().stream()
//                            .anyMatch(brand ->
//                                    brand.toLowerCase().equals(car.getBrand().toLowerCase())))
//                    .collect(Collectors.toList());
//        }
//        if (carQuizzModel.getFabricationYear() != null) {
//            carList = carList.stream()
//                    .filter(car -> car.getFabricationYear() >= carQuizzModel.getFabricationYear() - 2 && car.getFabricationYear() <= carQuizzModel.getFabricationYear() + 2)
//                    .collect(Collectors.toList());
//        }
//        if (carQuizzModel.getHorsePower() != null) {
//            carList = carList.stream()
//                    .filter(car -> car.getHorsePower() >= carQuizzModel.getHorsePower() - 10 && car.getHorsePower() <= carQuizzModel.getHorsePower() + 10)
//                    .collect(Collectors.toList());
//        }
//        if (carQuizzModel.getSeats() != null) {
//            carList = carList.stream()
//                    .filter(car -> car.getSeats() >= carQuizzModel.getSeats() && car.getSeats() <= carQuizzModel.getSeats() + 2)
//                    .collect(Collectors.toList());
//        }
//        if (carQuizzModel.getPricePerDay() != null) {
//            carList = carList.stream()
//                    .filter(car -> car.getPricePerDay() >= carQuizzModel.getPricePerDay() - 5 && car.getPricePerDay() <= carQuizzModel.getPricePerDay() + 5)
//                    .collect(Collectors.toList());
//        }
//        if (carQuizzModel.getEndDate() != null && carQuizzModel.getStartDate() != null) {
//            carList = carList.stream()
//                    .filter(car -> 0 == rentDetailRepository.checkCarForAvailability(car.getId(), carQuizzModel.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), carQuizzModel.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).size())
//                    .collect(Collectors.toList());
//        }
//        for (Car car : carList) {
//            carListModel.add(transformer.transformEntityToModel(car));
//        }
        return recommender.getRecommendation(userId, carQuizzModel);
        //recommender.calculateSimilarity();
       // return new ArrayList<>();
    }

    @Override
    public Car getCarEntity(String vehicleIdentificationNumber) throws ExceptionNotFound {
        Optional<Car> optionalCar = carRepository.findByVehicleIdentificationNumber(vehicleIdentificationNumber);
        if (!optionalCar.isPresent()) {
            throw new ExceptionNotFound("Car with vehicle file identification number: " + vehicleIdentificationNumber + " does not exist.");
        } else {
            return optionalCar.get();
        }
    }

    @Override
    public void updateCar(Car car) throws ExceptionNotFound {
        Optional<Car> optionalCar = carRepository.findByVehicleIdentificationNumber(car.getVehicleIdentificationNumber());
        if (optionalCar.isPresent()) {
            carRepository.save(car);

        } else {
            throw new ExceptionNotFound("Car with vehicle file identification number: " + car.getVehicleIdentificationNumber() + " does not exist.");
        }
    }

    @Override
    public List<CarModel> getCarTopCar() {
        List<Car> carList = carRepository.findTop3();
        List<CarModel> carListModel = new ArrayList<>();
        for (Car car : carList) {
            carListModel.add(transformer.transformEntityToModel(car));
        }
        return carListModel;
    }
}
