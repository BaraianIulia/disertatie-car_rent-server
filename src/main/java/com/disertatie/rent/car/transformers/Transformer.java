package com.disertatie.rent.car.transformers;

import com.disertatie.rent.car.entities.*;
import com.disertatie.rent.car.model.*;
import com.disertatie.rent.car.model.enumType.*;
import com.disertatie.rent.car.repository.CarRepository;
import com.disertatie.rent.car.repository.RentDetailRepository;
import com.disertatie.rent.car.repository.UserRepository;
import com.disertatie.rent.car.service.passwordEncoder.PasswordEncoder;
import com.disertatie.rent.car.transformers.utils.ColorUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;

@Component(value = "transformer")
public class Transformer {

    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Resource(name = "carRepository")
    private CarRepository carRepository;

    @Resource(name = "rentDetailRepository")
    private RentDetailRepository rentDetailRepository;

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
            car.setHexColor(ColorUtils.colorToHex(carModel.getColor()));
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
        carModel.setSeats(car.getSeats());
        carModel.setFabricationYear(car.getFabricationYear());
        carModel.setGearbox(car.getGearbox().toString());
        carModel.setPricePerDay(car.getPricePerDay());
        carModel.setInsurance(car.getInsurance());
        carModel.setHorsePower(car.getHorsePower());
        carModel.setColor(car.getColor());
        carModel.setHexColor(car.getHexColor());
        carModel.setConditionalAir(car.isConditionalAir());
        carModel.setFuelType(car.getFuelType().toString());
        carModel.setLuggageCarrierVolume(car.getLuggageCarrierVolume());
        if (null != car.getPhoto()) {
            carModel.setPhoto(new String(car.getPhoto(), StandardCharsets.UTF_8));
        }

        return carModel;

    }

    public Card transformModelToEntity(CardModel cardModel) {
        Card card = new Card();
        if (cardModel.getId() != null) {
            card.setId(cardModel.getId());
        }
        if (cardModel.getCardHolder() != null) {
            card.setCardHolder(cardModel.getCardHolder());
        }
        if (cardModel.getCardNumber() != null) {
            card.setCardNumber(cardModel.getCardNumber());
        }
        if (cardModel.getExpiryDate() != null) {
            card.setExpiryDate(cardModel.getExpiryDate());
        }
        if (cardModel.getCvvCode() != null) {
            card.setCvvCode(cardModel.getCvvCode());
        }
        if (cardModel.getUserId() != null) {
            card.setUser(userRepository.getOne(cardModel.getUserId()));
        }
        return card;
    }

    public CardModel transformEntityToModel(Card card) {
        CardModel cardModel = new CardModel();
        cardModel.setId(card.getId());
        cardModel.setCardHolder(card.getCardHolder());
        cardModel.setCardNumber(card.getCardNumber());
        cardModel.setExpiryDate(card.getExpiryDate());
        cardModel.setUserId(card.getUser().getId());

        return cardModel;
    }

    public RentDetail transformModelToEntity(RentDetailModel rentDetailModel) {
        RentDetail rentDetail = new RentDetail();
        if (rentDetailModel.getId() != null) {
            rentDetail.setId(rentDetailModel.getId());
        }
        if (rentDetailModel.getCarId() != null) {
            rentDetail.setCar(carRepository.getOne(rentDetailModel.getCarId()));
        }
        if (rentDetailModel.getStartDate() != null) {
            rentDetail.setStartDate(rentDetailModel.getStartDate());
        }
        if (rentDetailModel.getEndDate() != null) {
            rentDetail.setEndDate(rentDetailModel.getEndDate());
        }
        return rentDetail;
    }

    public RentDetailModel transformEntityToModel(RentDetail rentDetail) {
        RentDetailModel rentDetailModel = new RentDetailModel();
        rentDetailModel.setId(rentDetail.getId());
        rentDetailModel.setCarId(rentDetail.getCar().getId());
        rentDetailModel.setStartDate(rentDetail.getStartDate());
        rentDetailModel.setEndDate(rentDetail.getEndDate());

        return rentDetailModel;
    }

    public Receipt transformModelToEntity(OrderDetailModel orderDetailModel) {
        Receipt receipt = new Receipt();
        receipt.setTotalPrice(orderDetailModel.getTotalPrice());
        receipt.setUser(userRepository.getOne(orderDetailModel.getUserId()));
        receipt.setPayMethod(transformStringToEnum(orderDetailModel.getPayMethod()));
        receipt.setRentDetail(rentDetailRepository.getOne(orderDetailModel.getRentDetailId()));

        return receipt;
    }

    public RentDetail transformModelToEntityRentDetail(OrderDetailModel orderDetailModel) {
        RentDetail rentDetail = new RentDetail();
        rentDetail.setCar(carRepository.getOne(orderDetailModel.getCarId()));
        rentDetail.setStartDate(orderDetailModel.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        rentDetail.setEndDate(orderDetailModel.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        rentDetail.setPickupDropoffLocation(orderDetailModel.getPickupDropoffLocation());

        return rentDetail;
    }

    private PayMethodType transformStringToEnum(String payMethod) {
        if (payMethod.toLowerCase().contains("cash")) {
            return PayMethodType.CASH;
        }
        return PayMethodType.CARD;
    }

    public Comment transformModelToEntity(CommentModel commentModel) {
        Comment comment = new Comment();
        if (commentModel.getId() != null) {
            comment.setId(commentModel.getId());
        }
        if (commentModel.getCarId() != null) {
            comment.setCar(carRepository.getOne(commentModel.getCarId()));
        }
        if (!StringUtils.isEmpty(commentModel.getText())) {
            comment.setText(commentModel.getText());
        }
        if (!StringUtils.isEmpty(commentModel.getAuthor())) {
            comment.setAuthor(commentModel.getAuthor());
        }
        if (!StringUtils.isEmpty(commentModel.getAuthorEmail())) {
            comment.setAuthorEmail(commentModel.getAuthorEmail());
        }
        if (StringUtils.isEmpty(commentModel.getStatus())) {
            comment.setStatus(CommentType.PENDING);
        } else {
            comment.setStatus(CommentType.APPROVED);
        }
        if (commentModel.getRating() != null) {
            comment.setRating(commentModel.getRating());
        }
        comment.setCreated(LocalDate.now());

        return comment;
    }

    public CommentModel transformEntityToModel(Comment comment) {
        CommentModel commentModel = new CommentModel();

        commentModel.setId(comment.getId());
        commentModel.setCarId(comment.getCar().getId());
        commentModel.setText(comment.getText());
        commentModel.setAuthor(comment.getAuthor());
        commentModel.setAuthorEmail(comment.getAuthorEmail());
        commentModel.setStatus(comment.getStatus().toString());
        commentModel.setRating(comment.getRating());
        commentModel.setCreated(comment.getCreated());

        return commentModel;
    }
}
