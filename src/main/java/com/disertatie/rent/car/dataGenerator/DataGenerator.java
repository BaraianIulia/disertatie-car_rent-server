package com.disertatie.rent.car.dataGenerator;

import com.disertatie.rent.car.exceptions.ExceptionExistingCar;
import com.disertatie.rent.car.exceptions.ExceptionExistingUser;
import com.disertatie.rent.car.model.CarModel;
import com.disertatie.rent.car.model.CommentModel;
import com.disertatie.rent.car.model.UserModel;
import com.disertatie.rent.car.model.enumType.CommentType;
import com.disertatie.rent.car.model.enumType.FuelType;
import com.disertatie.rent.car.model.enumType.GearboxType;
import com.disertatie.rent.car.model.enumType.UserRoleType;
import com.disertatie.rent.car.service.CarService;
import com.disertatie.rent.car.service.CommentService;
import com.disertatie.rent.car.service.UserService;
import com.disertatie.rent.car.transformers.utils.ColorUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Component(value = "dataGenerator")
public class DataGenerator {

    @Resource(name = "carService")
    private CarService carService;

    @Resource(name = "userService")
    private UserService userService;


    @Resource(name = "commentService")
    private CommentService commentService;

    private List<String> brandList = Arrays.asList("BMW", "Audi", "Ford", "Dacia", "Mercedes-Benz", "Honda", "Hyundai", "Fiat");
    private List<String> modelBMWList = Arrays.asList("1 Series", "2 Series Convertible", "2 Series Gran Coupe", "3 Series Sedan", "4 Series Convertible");
    private List<String> modelAudiList = Arrays.asList("A1", "A2", "A3", "A4", "Q3", "Q5", "Q7", "TT");
    private List<String> modelFordList = Arrays.asList("Fiesta", "Focus", "Ka", "Figo", "Mondeo", "Taurus", "GT", "Mustang");
    private List<String> modelDaciaList = Arrays.asList("1300", "Solenza", "Logan", "Sandero", "Duster", "Dokker", "1100");
    private List<String> modelMercedesBenzList = Arrays.asList("A-Class", "B-Class", "C-Class", "EQS", "GLB-Class", "S-Class");
    private List<String> modelHondaList = Arrays.asList("Brio", "City", "Civic", "Accord", "Amaze", "Envix", "Crider");
    private List<String> modelHyundaiList = Arrays.asList("i10", "i20", "i30", "Santro", "Accent", "Aura", "Celesta");
    private List<String> modelFiatList = Arrays.asList("Mobi", "500", "Panda", "Uno", "Argo", "Cronos", "Tipo", "500X", "500L");
    private List<String> colorList = Arrays.asList("AliceBlue", "AntiqueWhite", "Aqua", "Aquamarine", "Azure", "Beige", "Bisque", "Black", "BlanchedAlmond", "Blue", "BlueViolet", "Brown",
            "BurlyWood", "CadetBlue", "Chartreuse", "Chocolate", "Coral", "CornflowerBlue", "Cornsilk", "Crimson", "Cyan", "DarkBlue", "DarkCyan", "DarkGoldenRod", "DarkGray", "DarkGreen",
            "DarkKhaki", "DarkMagenta", "DarkOliveGreen", "DarkOrange", "DarkOrchid", "DarkRed", "DarkSalmon", "DarkSeaGreen", "DarkSlateBlue", "DarkSlateGray", "DarkTurquoise", "DarkViolet",
            "DeepPink", "DeepSkyBlue", "DimGray", "DodgerBlue", "FireBrick", "FloralWhite", "ForestGreen", "Fuchsia", "Gainsboro", "GhostWhite", "Gold", "GoldenRod", "Gray", "Green", "GreenYellow",
            "HoneyDew", "HotPink", "IndianRed", "Indigo", "Ivory", "Khaki", "Lavender", "LavenderBlush", "LawnGreen", "LemonChiffon", "LightBlue", "LightCoral", "LightCyan", "LightGoldenRodYellow",
            "LightGray", "LightGreen", "LightPink", "LightSalmon", "LightSeaGreen", "LightSkyBlue", "LightSlateGray", "LightSteelBlue", "LightYellow", "Lime", "LimeGreen", "Linen", "Magenta", "Maroon",
            "MediumAquaMarine", "MediumBlue", "MediumOrchid", "MediumPurple", "MediumSeaGreen", "MediumSlateBlue", "MediumSpringGreen", "MediumTurquoise", "MediumVioletRed", "MidnightBlue", "MintCream",
            "MistyRose", "Moccasin", "NavajoWhite", "Navy", "OldLace", "Olive", "OliveDrab", "Orange", "Orchid", "PaleGoldenRod", "PaleGreen", "PaleTurquoise", "PaleVioletRed", "PapayaWhip", "PeachPuff", "Peru",
            "Pink", "Plum", "PowderBlue", "Purple", "Red", "RosyBrown", "RoyalBlue", "SaddleBrown", "Salmon", "SandyBrown", "SeaGreen", "SeaShell", "Sienna", "Silver", "SkyBlue", "SlateBlue", "SlateGray", "Snow",
            "SpringGreen", "SteelBlue", "Tan", "Teal", "Thistle", "Tomato", "Turquoise", "Violet", "Wheat", "White", "WhiteSmoke", "Yellow", "YellowGreen");
    private List<String> nameList = Arrays.asList("James", "Mary", "Robert", "Patricia", "John", "Jennifer", "Michael", "Linda", "David", "Elizabeth", "William", "Barbara", "Richard", "Susan",
            "Joseph", "Jessica", "Thomas", "Sarah", "Charles", "Karen", "Christopher", "Nancy", "Daniel", "Lisa", "Matthew", "Betty", "Anthony", "Margaret", "Mark", "Sandra", "Ashley",
            "Donald", "Kimberly", "Steven", "Emily", "Paul", "Donna", "Andrew", "Michelle", "Joshua", "Michelle", "Kenneth", "Dorothy", "Kevin", "Carol", "Brian");
    private List<String> surnameList = Arrays.asList("Pujols", "Edmonds", "Hamilton", "Trout", "Gonzalez", "Fielder", "Strasburg", "Bautista", "Nielsen", "Jensen", "Hansen", "Pedersen");
    private List<String> over4Stars = Arrays.asList("Awesome experience.", "All services were very good.", "Better than expected.", "Everything was as described.", "Very nice car", "Great pick up and quick service right next to the airport terminal.",
            "Nice guys will rent with them next year.", "Jorge at the front desk was very helpful.. great service and painless pick-up and drop-off. Will use this company again!",
            "Great experience.", "A very easy rent a car agency to work with.", "Excellent efficient service and great car.", "I will return for sure", "est customer service I experience from a rental car company anywhere!!!",
            "Thumbs up", "They were efficient and got me on and out quickly.", "They were so helpful and affordable and courteous I would rent from them all the time.", "Great value for money, great vehicles, very helpful staff. The best rental experience.");
    private List<String> rating3Stars = Arrays.asList("Good vehicle and services but the AC broke down", "Good vehicle, but the staff was not very friendly", "Good services, but I did not like the car",
            "Good car but they were late to give us the car", "Awesome vehicle but I had problems with the payment");
    private List<String> under2Stars = Arrays.asList("Horrible", "It makes some disturbing noises while driving.", "The AC broke down", "I did not like it");
    private List<String> streetName = Arrays.asList("Abby Park", "Adelaide Avenue", "Airplane Avenue", "Airport Avenue", "Barn Street", "Bay Avenue", "Beatles Avenue", "Central Cesta", "China Avenue", "Communal Square",
            "East Hills Avenue", "Flower Avenue", "Freedom Avenue", "Galghard Road", "Greenpark Avenue", "Hazlett Avenue", "Heritage Avenue", "Hot-air Ballon Avenue", "Lucy", "McCrooke Avenue", "Maple Street", "Mandarin Park Lane",
            "Museum Avenue", "Newport", "New Orleans", "Pine", "Princess Avenue");

    private static final int NO_OF_ENTRANCE_TO_GENERATE = 100;

    public DataGenerator() {
    }

    public void generateUserComments() {
        CommentModel commentModel = new CommentModel();

        List<UserModel> userModelList = userService.getAllUsers();
        List<CarModel> carModelList = carService.getAllCars(null, null);

        for (int i = 0; i < NO_OF_ENTRANCE_TO_GENERATE; i++) {
            UserModel userModel = userModelList.get(ThreadLocalRandom.current().nextInt(0, userModelList.size()));
            commentModel.setAuthorEmail(userModel.getEmail());
            commentModel.setAuthor(userModel.getName());
            commentModel.setRating((long) ThreadLocalRandom.current().nextInt(1, 5 + 1));
            commentModel.setCarId(carModelList.get(ThreadLocalRandom.current().nextInt(0, carModelList.size())).getId());
            commentModel.setStatus(CommentType.APPROVED.toString());
            commentModel.setText(generateRandomText(commentModel.getRating()));
            commentService.addComment(commentModel);
        }
    }

    private String generateRandomText(Long rating) {
        if (rating >= 4) {
            return over4Stars.get(ThreadLocalRandom.current().nextInt(0, over4Stars.size()));
        }
        if (rating >= 3) {
            return rating3Stars.get(ThreadLocalRandom.current().nextInt(0, rating3Stars.size()));
        }
        return under2Stars.get(ThreadLocalRandom.current().nextInt(0, under2Stars.size()));
    }

    public void generateUsers() {
        for (int i = 0; i < 10; i++) {
            UserModel userModel = new UserModel();
            userModel.setName(generateRandomName());
            userModel.setSurname(generateRandomSurname());
            userModel.setBirthdate(generateRandomBirthdate());
            userModel.setAddress(generateRandomAddress());
            userModel.setPhoto(generateProfilePicture());
            userModel.setPhone(generateRandomPhoneNumber());
            userModel.setEmail(userModel.getName().toLowerCase() + userModel.getSurname().toLowerCase() + "@gmail.com");
            userModel.setPassword(userModel.getName().toLowerCase());
            userModel.setUserRole(UserRoleType.USER_ROLE.toString());
            userModel.setStatus(true);
            try {
                userService.register(userModel);
            } catch (ExceptionExistingUser exceptionExistingUser) {
                exceptionExistingUser.printStackTrace();
            }
        }
    }

    private String generateProfilePicture() {
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(new ClassPathResource("/pictures/user-profile.jpg").getFile());

            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            System.out.println(encodedString);
            encodedString = "data:image/jpeg;base64," + encodedString;
            System.out.println(encodedString);
            return encodedString;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private LocalDate generateRandomBirthdate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -18);
        cal.add(Calendar.DATE, -1);
        Date date = cal.getTime();
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.YEAR, -70);
        cal1.add(Calendar.DATE, -1);
        Date date1 = cal1.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(date1.getTime(), date.getTime());
        return
                Instant.ofEpochMilli(randomMillisSinceEpoch).atZone(ZoneId.systemDefault()).toLocalDate();

    }

    private String generateRandomPhoneNumber() {
        return Long.toString(ThreadLocalRandom.current().nextLong(1000000000, 9999999999L));
    }

    private String generateRandomAddress() {
        return streetName.get(ThreadLocalRandom.current().nextInt(0, streetName.size())) + " Street " + ThreadLocalRandom.current().nextInt(1, 99999);
    }

    private String generateRandomSurname() {
        return surnameList.get(ThreadLocalRandom.current().nextInt(0, surnameList.size()));
    }

    private String generateRandomName() {
        return nameList.get(ThreadLocalRandom.current().nextInt(0, nameList.size()));
    }

    public void generateCars() {
        for (int i = 0; i < NO_OF_ENTRANCE_TO_GENERATE; i++) {
            CarModel carModel = new CarModel();
            carModel.setVehicleIdentificationNumber(generateRandomString(14));
            carModel.setBrand(pickRandomBrand());
            carModel.setModel(pickRandomModel(carModel.getBrand()));
            carModel.setFabricationYear(generateRandomFabricationYear());
            carModel.setSeats(5);
            carModel.setGearbox(generateRandomGearbxox());
            carModel.setLuggageCarrierVolume(generateRandomLuggageCarrierVolume());
            carModel.setDoors(5);
            carModel.setFuelType(generateRandomFuelType());
            carModel.setConditionalAir(generateRandomConditionalAir());
            carModel.setPricePerDay(generateRandomPricePerDay());
            carModel.setInsurance(generateRandomInsurance());
            carModel.setHorsePower(generateRandomHorsePower());
            carModel.setColor(generateRandomColor());
            carModel.setHexColor(ColorUtils.colorToHex(carModel.getColor()));
            carModel.setPhoto(generatePictureCar(carModel.getBrand(), carModel.getModel()));

            try {
                carService.addCar(carModel);
            } catch (ExceptionExistingCar exceptionExistingCar) {
                exceptionExistingCar.printStackTrace();
            }
        }
    }

    private String generatePictureCar(String brand, String model) {
        String filePath = null;
        System.out.println(brand);
        System.out.println(model);
        switch (brand) {

            case "BMW":
                switch (model) {
                    case "1 Series":
                        filePath = "pictures/BMW-Series1.jpg";
                        break;
                    case "2 Series Convertible":
                        filePath = "pictures/BMW-2SeriesConvertible.jpg";
                        break;
                    case "2 Series Gran Coupe":
                        filePath = "pictures/BMW-2SeriesGranCoupe.jpg";
                        break;
                    case "3 Series Sedan":
                        filePath = "pictures/BMW-3SeriesSedan.jpg";
                        break;
                    case "4 Series Convertible":
                        filePath = "pictures/BMW-4SeriesConvertible.jpg";
                        break;
                }
                break;
            case "Audi":
                switch (model) {
                    case "A1":
                        filePath = "pictures/Audi-A1.jpg";
                        break;
                    case "A2":
                        filePath = "pictures/Audi-A2V.jpg";
                        break;
                    case "A4":
                        filePath = "pictures/Audi-A4.jpg";
                        break;
                    case "A3":
                        filePath = "pictures/Audi-A3.jpg";
                        break;
                    case "Q3":
                        filePath = "pictures/Audi-Q3.jpg";
                        break;
                    case "Q5":
                        filePath = "pictures/Audi-Q5.jpg";
                        break;
                    case "Q7":
                        filePath = "pictures/Audi-Q7.jpg";
                        break;
                    case "TT":
                        filePath = "pictures/Audi-TT.jpg";
                        break;
                }
                break;
            case "Ford":
                switch (model) {
                    case "Fiesta":
                        filePath = "pictures/Ford-Fiesta.jpg";
                        break;
                    case "Focus":
                        filePath = "pictures/Ford-Focus.jpg";
                        break;
                    case "Ka":
                        filePath = "pictures/Ford-Ka.jpg";
                        break;
                    case "Figo":
                        filePath = "pictures/Ford-Figo.jpg";
                        break;
                    case "Mondeo":
                        filePath = "pictures/Ford-Mondeo.jpg";
                        break;
                    case "Taurus":
                        filePath = "pictures/Ford-Taurus.jpg";
                        break;
                    case "GT":
                        filePath = "pictures/Ford-GT.jpg";
                        break;
                    case "Mustang":
                        filePath = "pictures/Ford-Mustang.jpg";
                        break;
                }
                break;
            case "Dacia":
                switch (model) {
                    case "1300":
                        filePath = "pictures/Dacia-1300.jpg";
                        break;
                    case "Solenza":
                        filePath = "pictures/Dacia-Solenza.jpg";
                        break;
                    case "Logan":
                        filePath = "pictures/Dacia-Logan.jpg";
                        break;
                    case "Sandero":
                        filePath = "pictures/Dacia-Sandero.jpg";
                        break;
                    case "Duster":
                        filePath = "pictures/Dacia-Duster.jpg";
                        break;
                    case "Dokker":
                        filePath = "pictures/Dacia-Dokker.jpg";
                        break;
                    case "1100":
                        filePath = "pictures/Dacia-1100.jpg";
                        break;
                }
                break;
            case "Mercedes-Benz":
                switch (model) {
                    case "A-Class":
                        filePath = "pictures/Mercedes-Benz-A-Class.jpg";
                        break;
                    case "B-Class":
                        filePath = "pictures/Mercedes-Benz-B-Class.jpg";
                        break;
                    case "C-Class":
                        filePath = "pictures/Mercedes-Benz-C-Class.jpg";
                        break;
                    case "EQS":
                        filePath = "pictures/Mercedes-Benz-EQS.jpg";
                        break;
                    case "GLB-Class":
                        filePath = "pictures/Mercedes-Benz-GLB-Class.jpg";
                        break;
                    case "S-Class":
                        filePath = "pictures/Mercedes-Benz-S-Class.jpg";
                        break;
                }
                break;
            case "Honda":
                switch (model) {
                    case "Brio":
                        filePath = "pictures/Honda-Brio.jpg";
                        break;
                    case "City":
                        filePath = "pictures/Honda-City.jpg";
                        break;
                    case "Civic":
                        filePath = "pictures/Honda-Civic.jpg";
                        break;
                    case "Accord":
                        filePath = "pictures/Honda-Accord.jpg";
                        break;
                    case "Amaze":
                        filePath = "pictures/Honda-Amaze.jpeg";
                        break;
                    case "Envix":
                        filePath = "pictures/Honda-Envix.jpg";
                        break;
                    case "Crider":
                        filePath = "pictures/Honda-Crider.jpg";
                        break;
                }
                break;
            case "Hyundai":
                switch (model) {
                    case "i10":
                        filePath = "pictures/Hyundai-i10.jpg";
                        break;
                    case "i20":
                        filePath = "pictures/Hyundai-i20.jpg";
                        break;
                    case "i30":
                        filePath = "pictures/Hyundai-i30.jpg";
                        break;
                    case "Santro":
                        filePath = "pictures/Hyundai-Santro.jpg";
                        break;
                    case "Accent":
                        filePath = "pictures/Hyundai-Accent.jpg";
                        break;
                    case "Aura":
                        filePath = "pictures/Hyundai-Aura.jpeg";
                        break;
                    case "Celesta":
                        filePath = "pictures/Hyundai-Celesta.jpg";
                        break;
                }
                break;
            case "Fiat":
                switch (model) {
                    case "Mobi":
                        filePath = "pictures/Fiat-Mobi.jpg";
                        break;
                    case "500":
                        filePath = "pictures/Fiat-500.jpg";
                        break;
                    case "Panda":
                        filePath = "pictures/Fiat-Panda.jpg";
                        break;
                    case "Uno":
                        filePath = "pictures/Fiat-Uno.jpg";
                        break;
                    case "Argo":
                        filePath = "pictures/Fiat-Argo.jpg";
                        break;
                    case "Cronos":
                        filePath = "pictures/Fiat-Cronos.jpg";
                        break;
                    case "Tipo":
                        filePath = "pictures/Fiat-Tipo.jpg";
                        break;
                    case "500X":
                        filePath = "pictures/Fiat-500X.jpg";
                        break;
                    case "500L":
                        filePath = "pictures/Fiat-500L.jpg";
                        break;
                }
                break;
        }
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(new ClassPathResource(filePath).getFile());

            String encodedString = Base64.getEncoder().encodeToString(fileContent);

            encodedString = "data:image/jpeg;base64," + encodedString;

            return encodedString;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Double generateRandomInsurance() {
        return (double) ThreadLocalRandom.current().nextInt(10, 30);
    }

    private Double generateRandomHorsePower() {
        return (double) ThreadLocalRandom.current().nextInt(90, 110);
    }


    private String generateRandomColor() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, colorList.size());
        return colorList.get(randomNum);
    }

    private Double generateRandomPricePerDay() {
        return (double) ThreadLocalRandom.current().nextInt(25, 35);
    }

    private boolean generateRandomConditionalAir() {
        if (0 == ThreadLocalRandom.current().nextInt(0, 2)) {
            return true;
        }
        return false;
    }

    private String generateRandomFuelType() {
        if (0 == ThreadLocalRandom.current().nextInt(0, 2)) {
            return FuelType.DIESEL.toString();
        }
        return FuelType.GASOLINE.toString();
    }

    private Integer generateRandomLuggageCarrierVolume() {
        return ThreadLocalRandom.current().nextInt(1, 4);
    }

    private String generateRandomGearbxox() {
        if (0 == ThreadLocalRandom.current().nextInt(0, 2)) {
            return GearboxType.AUTOMATIC.toString();
        }
        return GearboxType.MANUAL.toString();
    }

    private Integer generateRandomFabricationYear() {
        return ThreadLocalRandom.current().nextInt(2007, 2013);
    }

    private String pickRandomModel(String brand) {
        if (brand.equals("BMW")) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, modelBMWList.size());
            return modelBMWList.get(randomNum);
        }
        if (brand.equals("Audi")) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, modelAudiList.size());
            return modelAudiList.get(randomNum);
        }
        if (brand.equals("Ford")) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, modelFordList.size());
            return modelFordList.get(randomNum);
        }
        if (brand.equals("Dacia")) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, modelDaciaList.size());
            return modelDaciaList.get(randomNum);
        }
        if (brand.equals("Mercedes-Benz")) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, modelMercedesBenzList.size());
            return modelMercedesBenzList.get(randomNum);
        }
        if (brand.equals("Honda")) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, modelHondaList.size());
            return modelHondaList.get(randomNum);
        }
        if (brand.equals("Hyundai")) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, modelHyundaiList.size());
            return modelHyundaiList.get(randomNum);
        }
        if (brand.equals("Fiat")) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, modelFiatList.size());
            return modelFiatList.get(randomNum);
        }
        return null;
    }

    private String pickRandomBrand() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, brandList.size());
        return brandList.get(randomNum);
    }

    private String generateRandomString(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
