package com.disertatie.rent.car.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "similarity_values", indexes = {@Index(name = "IDX_similarityForUser_carId", columnList = "similarity_for_user,car_id")})
public class SimilarityValue {

    @Id
    @GeneratedValue
    @Column(name = "similarity_values_id")
    private Long id;

    @Column(name = "similarity_for_user")
    private Long similarityForUser;

    @Column(name = "car_id")
    private Long carId;

    @Column(name = "value")
    private Double value;

    public SimilarityValue() {
    }
}
