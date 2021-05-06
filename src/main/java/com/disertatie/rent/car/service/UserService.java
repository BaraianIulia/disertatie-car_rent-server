package com.disertatie.rent.car.service;

import com.disertatie.rent.car.exceptions.*;
import com.disertatie.rent.car.model.UserModel;
import com.disertatie.rent.car.model.enumType.UserRoleType;

import java.util.List;

public interface UserService {

    List<UserModel> getAllUsers();

    UserModel login(String email, String password) throws ExceptionInvalidCredentials, ExceptionNotFound, ExceptionDeactivatedAccount;

    UserModel register(UserModel userModel) throws ExceptionExistingUser;

    UserModel changeRole(Long userId, UserRoleType userRole, Long currentUserId) throws ExceptionNotFound, ExceptionUnauthorizedAction;

    UserModel changeStatus(Long userId, boolean status, Long currentUserId) throws ExceptionNotFound, ExceptionUnauthorizedAction;

    UserModel editProfileData(UserModel userModel) throws ExceptionNotFound;

    void changePassword(Long userId, String actualPassword, String newPassword) throws ExceptionInvalidCredentials, ExceptionNotFound;

    UserModel getUserById(Long userId) throws ExceptionNotFound;

    void changeProfilePicture(Long userId, String photo) throws ExceptionNotFound, ExceptionInvalidData;
}
