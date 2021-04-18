package com.disertatie.rent.car.model;

import java.time.LocalDate;

public class OrderDetailModel {

    private Long userId;
    private Long carId;
    private Long cardId;
    private Long rentDetailId;
    private Long totalPrice;
    private Long insurance;
    private LocalDate startDate;
    private LocalDate endDate;
    private String pickupDropoffLocation;
    private String payMethod;
    private String cvvCode;
    private String cardHolder;
    private String expiryDate;
    private String cardNumber;

    public OrderDetailModel() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getInsurance() {
        return insurance;
    }

    public void setInsurance(Long insurance) {
        this.insurance = insurance;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getPickupDropoffLocation() {
        return pickupDropoffLocation;
    }

    public void setPickupDropoffLocation(String pickupDropoffLocation) {
        this.pickupDropoffLocation = pickupDropoffLocation;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getCvvCode() {
        return cvvCode;
    }

    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getRentDetailId() {
        return rentDetailId;
    }

    public void setRentDetailId(Long rentDetailId) {
        this.rentDetailId = rentDetailId;
    }
}
