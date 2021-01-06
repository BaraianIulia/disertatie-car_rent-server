package com.disertatie.rent.car.service;

import com.disertatie.rent.car.entities.User;
import com.disertatie.rent.car.exceptions.ExceptionExistingUser;
import com.disertatie.rent.car.exceptions.ExceptionInvalidLoginCredentials;
import com.disertatie.rent.car.exceptions.ExceptionNotFound;
import com.disertatie.rent.car.model.UserModel;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    UserModel login(String email, String password) throws ExceptionInvalidLoginCredentials, ExceptionNotFound;

    void register(UserModel userModel) throws ExceptionExistingUser;

    void changeRole(String role);
}
