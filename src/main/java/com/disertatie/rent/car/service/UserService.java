package com.disertatie.rent.car.service;

import com.disertatie.rent.car.exceptions.ExceptionExistingUser;
import com.disertatie.rent.car.exceptions.ExceptionInvalidCredentials;
import com.disertatie.rent.car.exceptions.ExceptionNotFound;
import com.disertatie.rent.car.exceptions.ExceptionUnauthorizedAction;
import com.disertatie.rent.car.model.UserModel;
import com.disertatie.rent.car.model.enumType.UserRoleEnum;

import java.util.List;

public interface UserService {

    List<UserModel> getAllUsers();

    UserModel login(String email, String password) throws ExceptionInvalidCredentials, ExceptionNotFound;

    void register(UserModel userModel) throws ExceptionExistingUser;


    UserModel changeRole(Long userId, UserRoleEnum userRole, Long currentUserId) throws ExceptionNotFound, ExceptionUnauthorizedAction;

    UserModel changeStatus(Long userId, boolean status, Long currentUserId) throws ExceptionNotFound, ExceptionUnauthorizedAction;

    UserModel editProfileData(UserModel userModel) throws ExceptionNotFound;

    void changePassword(Long userId, String actualPassword, String newPassword) throws ExceptionInvalidCredentials, ExceptionNotFound;
}
