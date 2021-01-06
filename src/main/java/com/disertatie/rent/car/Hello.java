package com.disertatie.rent.car;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class Hello {

    private final static Logger LOGGER = Logger.getLogger(Hello.class.getName());

    @GetMapping(path = "/", produces = "application/json")
    public String getAllUsers() {
        System.out.println("hjdgsaiudasgidas");
        LOGGER.info("UserController : getAllUsers()");
        return "HELLOOO";
    }
    }
