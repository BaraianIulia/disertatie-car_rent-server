package com.disertatie.rent.car.entities;

import com.disertatie.rent.car.model.enumType.PayMethodType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;

@Entity
@Getter
@Setter
@Table(name = "receipts")
public class Receipt {

    @Id
    @GeneratedValue
    @Column(name = "receipt_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rent_detail_id")
    private RentDetail rentDetail;

    @Column(name = "total_price")
    private Long totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "pay_method")
    private PayMethodType payMethod;

    @Column(name = "rent_date")
    private Date rentDate =  new java.sql.Date(Calendar.getInstance().getTime().getTime());

}
