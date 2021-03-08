package com.disertatie.rent.car.transformers;

import com.disertatie.rent.car.entities.Car;
import com.disertatie.rent.car.entities.User;
import com.disertatie.rent.car.model.CarModel;
import com.disertatie.rent.car.model.UserModel;
import com.disertatie.rent.car.model.enumType.FuelType;
import com.disertatie.rent.car.model.enumType.GearboxType;
import com.disertatie.rent.car.model.enumType.UserRoleType;
import com.disertatie.rent.car.service.passwordEncoder.PasswordEncoder;
import com.disertatie.rent.car.transformers.utils.ColorUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.awt.*;
import java.nio.charset.StandardCharsets;

@Component(value = "transformer")
public class Transformer {

    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Resource(name = "colorUtils")
    private ColorUtils colorUtils;

    public User transformModelToEntity(UserModel userModel) {
        User user = new User();
        if (null != userModel.getId()) {
            user.setId(userModel.getId());
        }
        if (userModel.getBirthdate() != null) {
            user.setName(userModel.getName());
        }
        if (userModel.getBirthdate() != null) {
            user.setSurname(userModel.getSurname());
        }
        if (userModel.getBirthdate() != null) {
            user.setBirthdate(java.sql.Date.valueOf(userModel.getBirthdate()));
        }
        if (userModel.getEmail() != null) {
            user.setEmail(userModel.getEmail());
        }

        if (userModel.getPassword() != null) {
            user.setPassword(passwordEncoder.encoder(userModel.getPassword()));
        }
        if (userModel.getPhone() != null) {
            user.setPhone(userModel.getPhone());
        }
        if (userModel.getAddress() != null) {
            user.setAddress(userModel.getAddress());
        }
        if (userModel.getPhoto() != null) {
            user.setPhoto(userModel.getPhoto().getBytes());
        }
        if (userModel.getUserRole().toLowerCase().contains("user")) {
            user.setUserRole(UserRoleType.USER_ROLE);
        } else if (userModel.getUserRole().toLowerCase().contains("admin")) {
            user.setUserRole(UserRoleType.ADMIN_ROLE);
        }
        user.setStatus(userModel.isStatus());
        if (!StringUtils.isEmpty(userModel.getPhoto())) {
            user.setPhoto(userModel.getPhoto().getBytes());
        }

        return user;
    }

    public UserModel transformEntityToModel(User user) {
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setName(user.getName());
        userModel.setSurname(user.getSurname());
        userModel.setBirthdate(user.getBirthdate().toLocalDate());
        userModel.setEmail(user.getEmail());
        userModel.setPhone(user.getPhone());
        userModel.setAddress(user.getAddress());
        if (user.getPhoto() != null) {
            userModel.setPhoto(new String(user.getPhoto(), StandardCharsets.UTF_8));
        }
        userModel.setUserRole(user.getUserRole().toString());
        userModel.setStatus(user.isStatus());
        if (user.getPhoto() != null) {
            userModel.setPhoto(new String(user.getPhoto(), StandardCharsets.UTF_8));
        }

        return userModel;

    }

    public Car transformModelToEntity(CarModel carModel) {
        Car car = new Car();
        if (null != carModel.getId()) {
            car.setId(carModel.getId());
        }
        if (null != carModel.getVehicleIdentificationNumber()) {
            car.setVehicleIdentificationNumber(carModel.getVehicleIdentificationNumber());
        }
        if (null != carModel.getBrand()) {
            car.setBrand(carModel.getBrand());
        }
        if (null != carModel.getModel()) {
            car.setModel(carModel.getModel());
        }
        if (null != carModel.getDoors()) {
            car.setDoors(carModel.getDoors());
        }
        if (null != carModel.getSeats()) {
            car.setSeats(carModel.getSeats());
        }
        if (null != carModel.getFabricationYear()) {
            car.setFabricationYear(carModel.getFabricationYear());
        }
        if (carModel.getGearbox().toLowerCase().contains("manual")) {
            car.setGearbox(GearboxType.MANUAL);
        } else if (carModel.getGearbox().toLowerCase().contains("automat")) {
            car.setGearbox(GearboxType.AUTOMATIC);
        }
        if (null != carModel.getPricePerDay()) {
            car.setPricePerDay(carModel.getPricePerDay());
        }
        if (null != carModel.getInsurance()) {
            car.setInsurance(carModel.getInsurance());
        }
        if (null != carModel.getHorsePower()) {
            car.setHorsePower(carModel.getHorsePower());
        }
        if (null != carModel.getColor()) {
            car.setColor(carModel.getColor());
            try {
                Color color = (Color) Color.class.getField(carModel.getColor().toUpperCase()).get(null);
                car.setHexColor(colorUtils.colorToHex(color));
            } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
                e.printStackTrace();
                return null;
            }
        }
        car.setConditionalAir(carModel.isConditionalAir());
        if (carModel.getFuelType().toLowerCase().contains("diesel")) {
            car.setFuelType(FuelType.DIESEL);
        } else if (carModel.getFuelType().toLowerCase().contains("gasoline")) {
            car.setFuelType(FuelType.GASOLINE);
        }
        if (null != carModel.getLuggageCarrierVolume()) {
            car.setLuggageCarrierVolume(carModel.getLuggageCarrierVolume());
        }
        if (!StringUtils.isEmpty(carModel.getPhoto())) {
            car.setPhoto(carModel.getPhoto().getBytes());
        }

        return car;
    }

    public CarModel transformEntityToModel(Car car) {
        CarModel carModel = new CarModel();
        carModel.setId(car.getId());
        carModel.setVehicleIdentificationNumber(car.getVehicleIdentificationNumber());
        carModel.setBrand(car.getBrand());
        carModel.setModel(car.getModel());
        carModel.setDoors(car.getDoors());
        carModel.setSeats(carModel.getSeats());
        carModel.setFabricationYear(carModel.getFabricationYear());
        carModel.setGearbox(car.getGearbox().toString());
        carModel.setPricePerDay(car.getPricePerDay());
        carModel.setInsurance(car.getInsurance());
        carModel.setHorsePower(car.getHorsePower());
        carModel.setColor(car.getColor());
        carModel.setHexColor(car.getHexColor());
        carModel.setConditionalAir(car.isConditionalAir());
        carModel.setFuelType(car.getFuelType().toString());
        carModel.setLuggageCarrierVolume(car.getLuggageCarrierVolume());
        carModel.setPhoto(new String(car.getPhoto(), StandardCharsets.UTF_8));

        return carModel;

    }


}
