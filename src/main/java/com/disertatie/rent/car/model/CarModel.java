package com.disertatie.rent.car.model;

public class CarModel {

    private Long id;
    private String vehicleIdentificationNumber;
    private String brand;
    private String model;
    private Integer doors;
    private Integer seats;
    private Integer fabricationYear;
    private String gearbox;
    private Double pricePerDay;
    private Double insurance;
    private Double horsePower;
    private Integer hexColor;
    private String color;
    private boolean conditionalAir;
    private String fuelType;
    private Integer luggageCarrierVolume;
    private String photo;

    public CarModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehicleIdentificationNumber() {
        return vehicleIdentificationNumber;
    }

    public void setVehicleIdentificationNumber(String vehicleIdentificationNumber) {
        this.vehicleIdentificationNumber = vehicleIdentificationNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getDoors() {
        return doors;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Integer getFabricationYear() {
        return fabricationYear;
    }

    public void setFabricationYear(Integer fabricationYear) {
        this.fabricationYear = fabricationYear;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Double getInsurance() {
        return insurance;
    }

    public void setInsurance(Double insurance) {
        this.insurance = insurance;
    }

    public Double getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(Double horsePower) {
        this.horsePower = horsePower;
    }

    public Integer getHexColor() {
        return hexColor;
    }

    public void setHexColor(Integer hexColor) {
        this.hexColor = hexColor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isConditionalAir() {
        return conditionalAir;
    }

    public void setConditionalAir(boolean conditionalAir) {
        this.conditionalAir = conditionalAir;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Integer getLuggageCarrierVolume() {
        return luggageCarrierVolume;
    }

    public void setLuggageCarrierVolume(Integer luggageCarrierVolume) {
        this.luggageCarrierVolume = luggageCarrierVolume;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
