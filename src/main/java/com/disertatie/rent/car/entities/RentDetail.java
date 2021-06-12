package com.disertatie.rent.car.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "rent_details", indexes = {@Index(name = "IDX_Start_End_Date", columnList = "start_date,end_date")})
public class RentDetail {

    @Id
    @GeneratedValue
    @Column(name = "rent_detail_id")
    private Long id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "pickup_dropoff_location")
    private String pickupDropoffLocation;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @OneToOne(mappedBy = "rentDetail")
    private Receipt receipt;

}
