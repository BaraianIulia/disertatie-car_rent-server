package com.disertatie.rent.car.entities;

import com.disertatie.rent.car.model.enumType.FuelType;
import com.disertatie.rent.car.model.enumType.GearboxType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="cars")
public class Car {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "vehicleIdentificationNumber")
    private String vehicleIdentificationNumber;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "doors")
    private Integer doors;

    @Column(name = "seats")
    private Integer seats;

    @Column(name = "fabricationYear")
    private Integer fabricationYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "gearbox")
    private GearboxType gearbox;

    @Column(name = "pricePerDay")
    private Double pricePerDay;

    @Column(name = "insurance")
    private Double insurance;

    @Column(name = "horsePower")
    private Double horsePower;

    @Column(name = "hexColor")
    private Integer hexColor;

    @Column(name = "color")
    private String color;

    @Column(name = "conditionalAir")
    private boolean conditionalAir;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuelType")
    private FuelType fuelType;

    @Column(name = "luggageCarrierVolume")
    private Integer luggageCarrierVolume;

    @Column(name = "photo")
    @Lob
    private byte[] photo;

    public Car() {
    }
}


