package com.disertatie.rent.car.controller;

import com.disertatie.rent.car.entities.User;
import com.disertatie.rent.car.exceptions.ExceptionExistingUser;
import com.disertatie.rent.car.exceptions.ExceptionInvalidLoginCredentials;
import com.disertatie.rent.car.exceptions.ExceptionNotFound;
import com.disertatie.rent.car.model.UserModel;
import com.disertatie.rent.car.service.UserService;
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
    public List<User> getAllUsers() {
        LOGGER.info("UserController : getAllUsers()");
        return userService.getAllUsers();
    }


    @PostMapping(path = "/login", produces = "application/json")
    public UserModel login(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) throws ExceptionInvalidLoginCredentials, ExceptionNotFound {
        LOGGER.info("UserController : login(" + email + " " + password + ")");
        return userService.login(email, password);
    }

    @PostMapping(path = "/register")
    public void register(@RequestBody UserModel userModel) throws ExceptionExistingUser {
        LOGGER.info("UserController : register "+ userModel.getEmail());
        userService.register(userModel);
    }


    @GetMapping(path = "/role")
    public void changeRole(@RequestParam(value = "role") String role) {
        userService.changeRole(role);
    }
}
