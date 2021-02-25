package com.disertatie.rent.car.controller;

import com.disertatie.rent.car.exceptions.ExceptionExistingUser;
import com.disertatie.rent.car.exceptions.ExceptionInvalidCredentials;
import com.disertatie.rent.car.exceptions.ExceptionNotFound;
import com.disertatie.rent.car.exceptions.ExceptionUnauthorizedAction;
import com.disertatie.rent.car.model.UserModel;
import com.disertatie.rent.car.model.enumType.UserRoleEnum;
import com.disertatie.rent.car.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final static Logger LOGGER = Logger.getLogger(UserController.class.getName());

    @Resource(name = "userService")
    private UserService userService;

    @GetMapping(path = "/list", produces = "application/json")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        LOGGER.info("UserController : getAllUsers()");
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping(path = "/login", produces = "application/json")
    public ResponseEntity<UserModel> login(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) throws ExceptionInvalidCredentials, ExceptionNotFound {
        LOGGER.info("UserController : login(" + email + " " + password + ")");
        return ResponseEntity.ok().body(userService.login(email, password));
    }

    @PostMapping(path = "/register")
    public ResponseEntity register(@RequestBody UserModel userModel) throws ExceptionExistingUser {
        LOGGER.info("UserController : register " + userModel.getEmail());
        userService.register(userModel);
        return ResponseEntity.ok().build();
    }


    @PostMapping(path = "/role")
    public ResponseEntity<UserModel> changeRole(@RequestParam(value = "userId") Long userId, @RequestParam(value = "userRole") String userRole, @RequestParam(value = "currentUserId") Long currentUserId) throws ExceptionUnauthorizedAction, ExceptionNotFound {
        if (userRole.toLowerCase().contains("admin")) {
            return ResponseEntity.ok().body(userService.changeRole(userId, UserRoleEnum.USER_ROLE, currentUserId));
        }
        return ResponseEntity.ok().body(userService.changeRole(userId, UserRoleEnum.ADMIN_ROLE, currentUserId));
    }

    @PostMapping(path = "/status")
    public ResponseEntity<UserModel> changeStatus(@RequestParam(value = "userId") Long userId, @RequestParam(value = "status") boolean status, @RequestParam(value = "currentUserId") Long currentUserId) throws ExceptionUnauthorizedAction, ExceptionNotFound {
        if (status) {
            return ResponseEntity.ok().body(userService.changeStatus(userId, false, currentUserId));
        }
        return ResponseEntity.ok().body(userService.changeStatus(userId, true, currentUserId));
    }

    @PostMapping(path = "/edit/profile")
    public ResponseEntity<UserModel> editProfileData(@RequestBody UserModel userModel) throws ExceptionNotFound {
        LOGGER.info("UserController : editProfileData " + userModel.getEmail());
        return ResponseEntity.ok().body(userService.editProfileData(userModel));
    }


    @PostMapping(path = "/edit/password", produces = "application/json")
    public ResponseEntity changePassword(@RequestParam(value = "userId") Long userId, @RequestParam(value = "actualPassword") String actualPassword, @RequestParam(value = "newPassword") String newPassword) throws ExceptionInvalidCredentials, ExceptionNotFound {
        LOGGER.info("UserController : changePassword(" + userId + " actual password" + actualPassword + " old password " + newPassword + ")");
        userService.changePassword(userId,actualPassword, newPassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/user")
    public ResponseEntity getUserById(@RequestParam(value = "userId") Long userId) throws ExceptionNotFound {
        LOGGER.info("UserController : getUserById " + userId);
        return ResponseEntity.ok().body( userService.getUserById(userId));
    }

}

