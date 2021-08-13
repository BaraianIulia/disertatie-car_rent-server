//package com.disertatie.rent.car.recommender;
//
//import com.disertatie.rent.car.entities.SimilarityValue;
//import com.disertatie.rent.car.exceptions.ExceptionNotFound;
//import com.disertatie.rent.car.model.CarModel;
//import com.disertatie.rent.car.model.CarQuizzModel;
//import com.disertatie.rent.car.model.CommentModel;
//import com.disertatie.rent.car.model.UserModel;
//import com.disertatie.rent.car.repository.Repo;
//import com.disertatie.rent.car.service.SimilarityValueService;
//import com.disertatie.rent.car.service.impl.CarServiceImp;
//import com.disertatie.rent.car.service.impl.CommentServiceImpl;
//import com.disertatie.rent.car.service.impl.UserServiceImp;
//import com.disertatie.rent.car.transformers.utils.ColorUtils;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Logger;
//import java.util.stream.Collectors;
//
//@Service(value = "recommendeer")
//@Transactional
//public class Recommendeer {
//
//    private final static Logger LOGGER = Logger.getLogger(Recommender.class.getName());
//
//    @Resource(name = "carService")
//    private CarServiceImp carService;
//
//    @Resource(name = "userService")
//    private UserServiceImp userService;
//
//    @Resource(name = "commentService")
//    private CommentServiceImpl commentService;
//
//    @Resource(name = "similarityValueService")
//    private SimilarityValueService similarityValueService;
//
//    @Resource(name = "repo")
//    private Repo repo;
//
//    private List<CarModel> filteredCarList = new ArrayList<>();
//    private Map<Long, Double> centeredCosineForCurrentCar = new HashMap<>();
//    private double normCurrentCar;
//    private Map<Long, Double> centeredSimilarity = new HashMap<>();
//    private double similarityDistance = 0;
//    private List<CommentModel> usersRatings = new ArrayList<>();
//    private List<CarModel> filteredCarListJob = new ArrayList<>();
//    private CarModel car;
//
//    public Recommendeer() {
//    }
//
//    public List<CarModel> getRecommendation(Long userId, CarQuizzModel carQuizzModel) throws ExceptionNotFound {
//        if (null == userId)
//            return null;
//        if (carQuizzModel == null)
//            return null;
//
//        UserModel userModel = userService.getUserById(userId);
//
//        filterCarList(carQuizzModel);
//        for (int i = 0; i < filteredCarList.size(); i++) {
//            double rating = similarityValueService.getRatingByUserIdAndCarId(userModel.getId(), filteredCarList.get(i).getId());
//            if (rating < 3.5) {
//                filteredCarList.remove(i);
//                i--;
//            }
//        }
//
//        return filteredCarList;
//    }
//
//    public void calculateSimilarity() {
//        similarityValueService.deleteAll();
//        filteredCarListJob = carService.getAllCars(null, null);
//        for (int i = 0; i < filteredCarListJob.size(); i++) {
//            LOGGER.info("Calculate similarity for user: " + filteredCarListJob.get(i).getId() + " " + filteredCarListJob.get(i).getModel() + " " + filteredCarListJob.get(i).getBrand());
//            this.car = filteredCarListJob.get(i);
//            centeredCosineForCurrentCar();
//            normCurrentCar = norm(centeredCosineForCurrentCar);
//            calculate(filteredCarListJob.get(i));
//            calculateRecommendation();
//        }
//
//    }
//
//    private void calculate(CarModel car) {
//        centeredSimilarity = new HashMap<>();
//        List<CarModel> carModelList = filteredCarListJob;
//        carModelList.remove(car);
//        for (CarModel carModel : filteredCarListJob
//        ) {
//            calculateCarRating(carModel);
//        }
//    }
//
//    private void centeredCosineForCurrentCar() {
//        double sum = 0;
//        int noOfRatings = 0;
//        double mediaAritmetica;
//
//
//        List<CommentModel> userComments = commentService.getAllByCarId(car.getId());
//        concatenateCommentsForTheSameCar(userComments);
//        List<UserModel> userModelList = userService.getAllUsers();
//        for (CommentModel userComment : userComments) {
//            sum = sum + userComment.getRating();
//            noOfRatings = noOfRatings + 1;
//        }
//        mediaAritmetica = sum / noOfRatings;
//        for (UserModel userModel : userModelList
//        ) {
//            if (userComments.stream().anyMatch(x -> x.getAuthorEmail().equals(userModel.getEmail()))) {
//                centeredCosineForCurrentCar.put(userModel.getId(), userComments.stream().filter(x -> x.getAuthorEmail().equals(userModel.getEmail())).collect(Collectors.toList()).get(0).getRating() - mediaAritmetica);
//            } else {
//                centeredCosineForCurrentCar.put(userModel.getId(), (double) 0);
//            }
//        }
//    }
//
//    private void calculateRecommendation() {
//        List<UserModel> userModelList = userService.getAllUsers();
//        for (UserModel userModel : userModelList) {
//            List<CommentModel> commentModelList = commentService.getUserCommentByCarId(userModel.getEmail(), this.car.getId());
//            if (commentModelList.size() > 0) {
//                double averageRating = commentModelList.stream().mapToDouble(CommentModel::getRating).sum() / commentModelList.size();
//                LOGGER.info("Final rating for user and car: " + " " + this.car.getId() + userModel.getEmail() + " " + averageRating);
//                SimilarityValue similarityValue = new SimilarityValue();
//                similarityValue.setCarId(this.car.getId());
//                similarityValue.setSimilarityForUser(userModel.getId());
//                similarityValue.setValue(averageRating);
//                similarityValueService.save(similarityValue);
//            } else {
//
//                similarityDistance = 0;
//                double finalRating = getMostSimilarUsers(userModel);
//                LOGGER.info("Final rating for user and car: " + " " + this.car.getId() + userModel.getEmail() + " " + finalRating);
//                SimilarityValue similarityValue = new SimilarityValue();
//                similarityValue.setCarId(this.car.getId());
//                similarityValue.setSimilarityForUser(userModel.getId());
//                similarityValue.setValue(finalRating);
//                similarityValueService.save(similarityValue);
//            }
//
//        }
//    }
//
//
//    private void calculateCarRating(CarModel car) {
//        usersRatings = commentService.getCommentsByCarId(car.getId());
//        concatenateCommentsForTheSameCar(usersRatings);
//        centeredCosine(car);
//
//
//    }
//
//    private void centeredCosine(CarModel car) {
//        double sum = 0;
//        int noOfRatings = 0;
//        double mediaAritmetica;
//
//        List<UserModel> userModelList = userService.getAllUsers();
//        for (CommentModel userComment : usersRatings) {
//            sum = sum + userComment.getRating();
//            noOfRatings = noOfRatings + 1;
//        }
//        mediaAritmetica = sum / noOfRatings;
//        Map<Long, Double> centeredCosine = new HashMap<>();
//        for (UserModel userModel : userModelList
//        ) {
//            if (usersRatings.stream().anyMatch(x -> x.getAuthorEmail().equals(userModel.getEmail()))) {
//                centeredCosine.put(userModel.getId(), usersRatings.stream().filter(x -> x.getAuthorEmail().equals(userModel.getEmail())).collect(Collectors.toList()).get(0).getRating() - mediaAritmetica);
//            } else {
//                centeredCosine.put(userModel.getId(), (double) 0);
//            }
//        }
//        cosineSimilarity(centeredCosine, car);
//    }
//
//    private void concatenateCommentsForTheSameCar(List<CommentModel> usersRatings) {
//        long averageRating;
//        for (int i = 0; i < usersRatings.size(); i++) {
//            int finalI = i;
//            List<CommentModel> commentsOnSameCar = usersRatings.stream()
//                    .filter(x -> x.getCarId() == usersRatings.get(finalI).getCarId() && x.getAuthorEmail().equals(usersRatings.get(finalI).getAuthorEmail()))
//                    .collect(Collectors.toList());
//            if (commentsOnSameCar.size() > 1) {
//                averageRating = commentsOnSameCar.stream().mapToLong(CommentModel::getRating).sum() / commentsOnSameCar.size();
//                CommentModel commentModelAverage = commentsOnSameCar.get(0);
//                commentModelAverage.setRating(averageRating);
//                for (CommentModel comm : commentsOnSameCar) {
//                    usersRatings.remove(comm);
//                }
//                usersRatings.add(commentModelAverage);
//                i--;
//            }
//        }
//    }
//
//    private void cosineSimilarity(Map<Long, Double> centeredCosineForCarsUser, CarModel car) {
//        double userMultiplyCurrentUser = 0;
//        for (Map.Entry<Long, Double> entry : centeredCosineForCarsUser.entrySet()) {
//            userMultiplyCurrentUser = userMultiplyCurrentUser + entry.getValue() * centeredCosineForCurrentCar.get(entry.getKey());
//        }
//        double similarityFormula = userMultiplyCurrentUser / (normCurrentCar * norm(centeredCosineForCarsUser));
//        centeredSimilarity.put(car.getId(), similarityFormula);
//
//    }
//
//    public static double norm(Map<Long, Double> centeredCosineForCarsUser) {
//        int norm = 0;
//        for (Map.Entry<Long, Double> entry : centeredCosineForCarsUser.entrySet()) {
//            norm += entry.getValue() * entry.getValue();
//        }
//        return Math.sqrt(norm);
//    }
//
//    private double getMostSimilarUsers(UserModel userModel) {
//        List<Long> mostSimilarUsers = new ArrayList<>();
//        if (centeredSimilarity.size() == 0) {
//            return 1;
//        }
//        while (mostSimilarUsers.size() <= centeredSimilarity.size() * 0.2) {
//            mostSimilarUsers = new ArrayList<>();
//            similarityDistance = similarityDistance + 0.2;
//            for (Map.Entry<Long, Double> entry : centeredSimilarity.entrySet()) {
//                if (entry.getValue() > normCurrentCar - similarityDistance && entry.getValue() < normCurrentCar + similarityDistance) {
//                    mostSimilarUsers.add(entry.getKey());
//                }
//            }
//        }
//        return calculateFinalCarRatingValue(mostSimilarUsers, userModel);
//
//    }
//
//    private double calculateFinalCarRatingValue(List<Long> mostSimilarUsers, UserModel userModel) {
//        double ratingSum = 0;
//        double numberOfRatings = 0;
//        double mediaAritmetica;
//        List<CommentModel> mostSimmilarComments = commentService.getAllUserComentsByIds(userModel.getEmail(), mostSimilarUsers);
//
//        if (mostSimmilarComments.size() == 0) {
//            return 1;
//        }
//        for (CommentModel comment : mostSimmilarComments) {
//            ratingSum = ratingSum + centeredSimilarity.get(comment.getCarId()) * comment.getRating();
//            numberOfRatings = centeredSimilarity.get(comment.getCarId()) + numberOfRatings;
//        }
//        return ratingSum / numberOfRatings;
//    }
//
//    private void filterCarList(CarQuizzModel carQuizzModel) {
//
//        filteredCarList = repo.findAllFiltered(carQuizzModel);
////        if (carQuizzModel.getStartDate() != null && carQuizzModel.getEndDate() != null) {
////            filteredCarList = carService.getAllCars(carQuizzModel.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), carQuizzModel.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
////        } else {
////            filteredCarList = carService.getAllCars(null, null);
////        }
//        if (carQuizzModel.getColor() != null) {
//            if (carQuizzModel.getColor().size() == 1) {
//                if (carQuizzModel.getColor().get(0).toLowerCase().contains("dark")) {
//                    filteredCarList =
//                            filteredCarList.stream()
//                                    .filter(e -> ColorUtils.isDarkColor(e.getColor()))
//                                    .collect(Collectors.toList());
//                } else {
//                    filteredCarList =
//                            filteredCarList.stream()
//                                    .filter(e -> !ColorUtils.isDarkColor(e.getColor()))
//                                    .collect(Collectors.toList());
//                }
//            }
//        }
////        if (carQuizzModel.getStartDate() != null && carQuizzModel.getEndDate() != null) {
////            LocalDate startDate = carQuizzModel.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
////            LocalDate endDate = carQuizzModel.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
////            filteredCarList = filteredCarList.stream().filter(x -> carService.checkCarForAvailability(x.getId(), startDate, endDate) == true).collect(Collectors.toList());
////        }
//
////        if (carQuizzModel.getBrand().size() > 0) {
////            filteredCarList =
////                    filteredCarList.stream()
////                            .filter(e -> carQuizzModel.getBrand().stream().anyMatch(name -> name.equals(e.getBrand())))
////                            .collect(Collectors.toList());
////        }
////        if (carQuizzModel.getFabricationYear() != null) {
////            filteredCarList = filteredCarList.stream()
////                    .filter(c -> c.getFabricationYear() >= carQuizzModel.getFabricationYear() - 2 && c.getFabricationYear() <= carQuizzModel.getFabricationYear() + 2)
////                    .collect(Collectors.toList());
////        }
////        if (carQuizzModel.getPricePerDay() != null) {
////            filteredCarList = filteredCarList.stream()
////                    .filter(c -> c.getPricePerDay() >= carQuizzModel.getPricePerDay() - 5 && c.getPricePerDay() <= carQuizzModel.getPricePerDay() + 5)
////                    .collect(Collectors.toList());
////        }
////        if (carQuizzModel.getHorsePower() != null) {
////            filteredCarList = filteredCarList.stream()
////                    .filter(c -> c.getHorsePower() >= carQuizzModel.getHorsePower() - 10 && c.getHorsePower() <= carQuizzModel.getHorsePower() + 10)
////                    .collect(Collectors.toList());
////        }
////        if (carQuizzModel.getSeats() != null) {
////            filteredCarList = filteredCarList.stream()
////                    .filter(c -> c.getSeats() >= carQuizzModel.getSeats() && c.getSeats() <= carQuizzModel.getSeats() + 2)
////                    .collect(Collectors.toList());
////        }
//    }
//}