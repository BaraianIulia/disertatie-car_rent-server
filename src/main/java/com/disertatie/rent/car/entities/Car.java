package com.disertatie.rent.car.entities;

import com.disertatie.rent.car.model.enumType.FuelType;
import com.disertatie.rent.car.model.enumType.GearboxType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="cars")
public class Car {

    @Id
    @GeneratedValue
    @Column(name = "car_id")
    private Long id;

    @Column(name = "vehicle_identification_number")
    private String vehicleIdentificationNumber;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "doors")
    private Integer doors;

    @Column(name = "seats")
    private Integer seats;

    @Column(name = "fabrication_year")
    private Integer fabricationYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "gearbox")
    private GearboxType gearbox;

    @Column(name = "price_per_day")
    private Double pricePerDay;

    @Column(name = "insurance")
    private Double insurance;

    @Column(name = "horse_power")
    private Double horsePower;

    @Column(name = "hex_color")
    private String hexColor;

    @Column(name = "color")
    private String color;

    @Column(name = "conditional_air")
    private boolean conditionalAir;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type")
    private FuelType fuelType;

    @Column(name = "luggage_carrier_volume")
    private Integer luggageCarrierVolume;

    @Column(name = "photo")
    @Lob
    private byte[] photo;

    @OneToMany(mappedBy="car", cascade = CascadeType.ALL)
    private Set<RentDetail> rentDetailsSet;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private Set<Comment> commentSet;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private CarTotalRating carTotalRating;


    public Car() {
    }
}


