package com.disertatie.rent.car.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "car_total_ratings")
public class CarTotalRating {
    @Id
    @GeneratedValue
    @Column(name = "car_total_rating_id")
    private Long id;

    @Column(name = "rating")
    private Long rating;

    @OneToOne
    @MapsId
    @JoinColumn(name = "car_id")
    private Car car;

    public CarTotalRating() {
    }
}
