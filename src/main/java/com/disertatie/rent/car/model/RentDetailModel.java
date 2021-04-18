package com.disertatie.rent.car.model;

import com.disertatie.rent.car.entities.Car;

import javax.persistence.*;
import java.time.LocalDate;

public class RentDetailModel {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long carId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
}
