package com.disertatie.rent.car.entities;

import com.disertatie.rent.car.model.enumType.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
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
    private byte[] photo;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRoleEnum userRole;

    //todo card id

    public User() {
    }
}
