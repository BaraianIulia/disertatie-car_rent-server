package com.disertatie.rent.car.recommender;

import com.disertatie.rent.car.controller.CarController;
import com.disertatie.rent.car.exceptions.ExceptionNotFound;
import com.disertatie.rent.car.model.CarModel;
import com.disertatie.rent.car.model.CarQuizzModel;
import com.disertatie.rent.car.model.CommentModel;
import com.disertatie.rent.car.model.UserModel;
import com.disertatie.rent.car.service.impl.CarServiceImp;
import com.disertatie.rent.car.service.impl.CommentServiceImpl;
import com.disertatie.rent.car.service.impl.UserServiceImp;
import com.disertatie.rent.car.transformers.utils.ColorUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service(value = "recommender")
@Transactional
public class Recommender {

    private final static Logger LOGGER = Logger.getLogger(Recommender.class.getName());

    @Resource(name = "carService")
    private CarServiceImp carService;

    @Resource(name = "userService")
    private UserServiceImp userService;

    @Resource(name = "commentService")
    private CommentServiceImpl commentService;

    private UserModel user;
    private List<CarModel> filteredCarList = new ArrayList<>();
    private Map<Long, Double> centeredCosineForCurrentUser = new HashMap<>();
    private double normCurrentUser;
    private Map<String, Double> centeredSimilarity = new HashMap<>();
    private double similarityDistance = 0.2;
    private List<CommentModel> usersRatings;

    public Recommender() {
    }

    public List<CarModel> getRecommendation(Long userId, CarQuizzModel carQuizzModel) throws ExceptionNotFound {
        if (null == userId)
            return null;
        if (carQuizzModel == null)
            return null;

        user = userService.getUserById(userId);

        filterCarList(carQuizzModel);

        LOGGER.info("Filtered Car List: " + filteredCarList.size());
        centeredCosineForCurrentUser();
        normCurrentUser = norm(centeredCosineForCurrentUser);
        calculateRecommendation();

        return filteredCarList;
    }

    private void centeredCosineForCurrentUser() {
        double sum = 0;
        int noOfRatings = 0;
        double mediaAritmetica;

        List<CommentModel> userComments = commentService.getAllUserCommentsByAuthorEmail(user.getEmail());
        List<CarModel> carList = carService.getAllCars(null, null);
        for (CommentModel userComment : userComments) {
            sum = sum + userComment.getRating();
            noOfRatings = noOfRatings + 1;
        }
        mediaAritmetica = sum / noOfRatings;
        for (CarModel car : carList
        ) {
            if (userComments.stream().anyMatch(x -> x.getCarId() == car.getId())) {
                centeredCosineForCurrentUser.put(car.getId(), userComments.stream().filter(x -> x.getCarId() == car.getId()).collect(Collectors.toList()).get(0).getRating() - mediaAritmetica);
            } else {
                centeredCosineForCurrentUser.put(car.getId(), (double) 0);
            }
        }
    }

    private void calculateRecommendation() {
        for (int i = 0; i < filteredCarList.size(); i++) {
            List<CommentModel> userCommentForCar = commentService.getUserCommentByCarId(user.getEmail(), filteredCarList.get(i).getId());
            if (userCommentForCar.size() == 0) {
                centeredSimilarity = new HashMap<>();
                calculateCarRating(filteredCarList.get(i));
                double finalRating = getMostSimilarUsers();
                LOGGER.info("FINAL RATING : " + finalRating);
                LOGGER.info("FOR CAR : " + filteredCarList.get(i).getId());
                if (finalRating < 3.8) {
                    filteredCarList.remove(filteredCarList.get(i));
                    i--;
                }
            } else {
                double averageRating = userCommentForCar.stream().mapToDouble(CommentModel::getRating).sum() / userCommentForCar.size();
                if (averageRating < 4) {
                    filteredCarList.remove(filteredCarList.get(i));
                    i--;
                }
            }
        }
    }


    private void calculateCarRating(CarModel car) {
        usersRatings = commentService.getCommentsByCarId(car.getId());
        concatenateCommentsForTheSameCar();
        for (CommentModel comment : usersRatings
        ) {
            centeredCosine(comment.getAuthorEmail());
        }


    }

    private void concatenateCommentsForTheSameCar() {
        long averageRating;
        for (int i = 0; i < usersRatings.size(); i++) {
            int finalI = i;
            List<CommentModel> commentsOnSameCar = usersRatings.stream()
                    .filter(x -> x.getCarId() == usersRatings.get(finalI).getCarId() && x.getAuthorEmail().equals(usersRatings.get(finalI).getAuthorEmail()))
                    .collect(Collectors.toList());
            if (commentsOnSameCar.size() > 1) {
                averageRating = commentsOnSameCar.stream().mapToLong(CommentModel::getRating).sum() / commentsOnSameCar.size();
                CommentModel commentModelAverage = commentsOnSameCar.get(0);
                commentModelAverage.setRating(averageRating);
                for (CommentModel comm : commentsOnSameCar) {
                    usersRatings.remove(comm);
                }
                usersRatings.add(commentModelAverage);
                i--;
            }
        }
    }

    private void centeredCosine(String authorEmail) {
        double sum = 0;
        int noOfRatings = 0;
        double mediaAritmetica;
        Map<Long, Double> centeredCosineForCarsUser = new HashMap<>();
        List<CommentModel> userComments = commentService.getAllUserCommentsByAuthorEmail(authorEmail);
        List<CarModel> carList = carService.getAllCars(null, null);
        for (CommentModel userComment : userComments) {
            sum = sum + userComment.getRating();
            noOfRatings = noOfRatings + 1;
        }
        mediaAritmetica = sum / noOfRatings;
        for (CarModel car : carList
        ) {
            if (userComments.stream().anyMatch(x -> x.getCarId() == car.getId())) {
                centeredCosineForCarsUser.put(car.getId(), userComments.stream().filter(x -> x.getCarId() == car.getId()).collect(Collectors.toList()).get(0).getRating() - mediaAritmetica);
            } else {
                centeredCosineForCarsUser.put(car.getId(), (double) 0);
            }
        }
        cosineSimilarity(centeredCosineForCarsUser, authorEmail);
    }

    private void cosineSimilarity(Map<Long, Double> centeredCosineForCarsUser, String authorEmail) {
        double userMultiplyCurrentUser = 0;
        for (Map.Entry<Long, Double> entry : centeredCosineForCarsUser.entrySet()) {
            userMultiplyCurrentUser = userMultiplyCurrentUser + entry.getValue() * centeredCosineForCurrentUser.get(entry.getKey());
        }
        double similarityFormula = userMultiplyCurrentUser / (normCurrentUser * norm(centeredCosineForCarsUser));
        centeredSimilarity.put(authorEmail, similarityFormula);

    }

    public static double norm(Map<Long, Double> centeredCosineForCarsUser) {
        int norm = 0;
        for (Map.Entry<Long, Double> entry : centeredCosineForCarsUser.entrySet()) {
            norm += entry.getValue() * entry.getValue();
        }
        return Math.sqrt(norm);
    }

    private double getMostSimilarUsers() {
        List<String> mostSimilarUsers = new ArrayList<>();
        if (centeredSimilarity.size() == 0) {
            return 1;
        }
        for (Map.Entry<String, Double> entry : centeredSimilarity.entrySet()) {
            if (entry.getValue() > normCurrentUser - similarityDistance && entry.getValue() < normCurrentUser + similarityDistance) {
                mostSimilarUsers.add(entry.getKey());
            }
        }
        if (mostSimilarUsers.size() >= centeredSimilarity.size() * 0.2) {
            return calculateFinalCarRatingValue(mostSimilarUsers);
        } else {
            similarityDistance = similarityDistance + 0.2;
            getMostSimilarUsers();
        }
        return 1;
    }

    private double calculateFinalCarRatingValue(List<String> mostSimilarUsers) {
        double ratingSum = 0;
        double numberOfRatings = 0;
        double mediaAritmetica;
        List<CommentModel> mostSimmilarComments = usersRatings.stream()
                .filter(e -> mostSimilarUsers.stream().anyMatch(name -> name.equals(e.getAuthorEmail())))
                .collect(Collectors.toList());

        for (CommentModel comment : mostSimmilarComments) {
            ratingSum = ratingSum + centeredSimilarity.get(comment.getAuthorEmail()) * comment.getRating();
            numberOfRatings = centeredSimilarity.get(comment.getAuthorEmail()) + numberOfRatings;
        }
        return ratingSum / numberOfRatings;
    }

    private void filterCarList(CarQuizzModel carQuizzModel) {
        if (carQuizzModel.getStartDate() != null && carQuizzModel.getEndDate() != null) {
            filteredCarList = carService.getAllCars(carQuizzModel.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), carQuizzModel.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        } else {
            filteredCarList = carService.getAllCars(null, null);
        }
        if (carQuizzModel.getColor() != null) {
            if (carQuizzModel.getColor().size() == 1) {
                if (carQuizzModel.getColor().get(0).toLowerCase().contains("dark")) {
                    filteredCarList =
                            filteredCarList.stream()
                                    .filter(e -> ColorUtils.isDarkColor(e.getColor()))
                                    .collect(Collectors.toList());
                } else {
                    filteredCarList =
                            filteredCarList.stream()
                                    .filter(e -> !ColorUtils.isDarkColor(e.getColor()))
                                    .collect(Collectors.toList());
                }
            }
        }
        if (carQuizzModel.getBrand().size() > 0) {
            filteredCarList =
                    filteredCarList.stream()
                            .filter(e -> carQuizzModel.getBrand().stream().anyMatch(name -> name.equals(e.getBrand())))
                            .collect(Collectors.toList());
        }
        if (carQuizzModel.getFabricationYear() != null) {
            filteredCarList = filteredCarList.stream()
                    .filter(c -> c.getFabricationYear() >= carQuizzModel.getFabricationYear() - 2 && c.getFabricationYear() <= carQuizzModel.getFabricationYear() + 2)
                    .collect(Collectors.toList());
        }
        if (carQuizzModel.getPricePerDay() != null) {
            filteredCarList = filteredCarList.stream()
                    .filter(c -> c.getPricePerDay() >= carQuizzModel.getPricePerDay() - 5 && c.getPricePerDay() <= carQuizzModel.getPricePerDay() + 5)
                    .collect(Collectors.toList());
        }
        if (carQuizzModel.getHorsePower() != null) {
            filteredCarList = filteredCarList.stream()
                    .filter(c -> c.getHorsePower() >= carQuizzModel.getHorsePower() - 10 && c.getHorsePower() <= carQuizzModel.getHorsePower() + 10)
                    .collect(Collectors.toList());
        }
        if (carQuizzModel.getSeats() != null) {
            filteredCarList = filteredCarList.stream()
                    .filter(c -> c.getSeats() >= carQuizzModel.getSeats() && c.getSeats() <= carQuizzModel.getSeats() + 2)
                    .collect(Collectors.toList());
        }
    }
}