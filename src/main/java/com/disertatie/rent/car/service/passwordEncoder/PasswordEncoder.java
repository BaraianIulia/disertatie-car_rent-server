package com.disertatie.rent.car.service.passwordEncoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component(value = "passwordEncoder")
public class PasswordEncoder {

    public String encoder(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public boolean check(String passwordUser, String passwordDb) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(passwordUser, passwordDb);
    }
}