package com.disertatie.rent.car.entities;

import com.disertatie.rent.car.model.enumType.UserRoleType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "photo")
    @Lob
    private byte[] photo;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRoleType userRole;

    @Column(name = "status")
    private boolean status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Card> cardSet;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Receipt> receiptSet;

    public User() {
    }
}
