package com.disertatie.rent.car.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue
    @Column(name = "card_id")
    private Long id;

    @Column(name = "card_holder")
    private String cardHolder;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "expiry_date")
    private String expiryDate;

    @Column(name = "cvv_code")
    private String cvvCode;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
