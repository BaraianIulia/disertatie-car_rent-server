package com.disertatie.rent.car.service.impl;

import com.disertatie.rent.car.entities.User;
import com.disertatie.rent.car.exceptions.ExceptionExistingUser;
import com.disertatie.rent.car.exceptions.ExceptionInvalidLoginCredentials;
import com.disertatie.rent.car.exceptions.ExceptionNotFound;
import com.disertatie.rent.car.model.UserModel;
import com.disertatie.rent.car.repository.UserRepository;
import com.disertatie.rent.car.service.UserService;
import com.disertatie.rent.car.service.passwordEncoder.PasswordEncoder;
import com.disertatie.rent.car.transformers.Transformer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImp implements UserService {

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Resource(name = "transformer")
    private Transformer transformer;


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserModel login(String email, String password) throws ExceptionInvalidLoginCredentials, ExceptionNotFound {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.check(user.getPassword())) {
                return transformer.transformEntityToModel(user);
            } else {
                throw new ExceptionInvalidLoginCredentials("Incorrect email or password;");
            }
        } else {
            throw new ExceptionNotFound("User with email: " + email + " not found.");
        }
    }

    @Override
    public void register(UserModel userModel) throws ExceptionExistingUser {
        Optional<User> optionalUser = userRepository.findByEmail(userModel.getEmail());
        if (optionalUser.isPresent()) {
            throw new ExceptionExistingUser("User with email: " + userModel.getEmail() + " already exists.");
        } else {
            userRepository.save(transformer.transformModelToEntity(userModel));
        }
    }

    @Override
    public void changeRole(String role) {

    }
}
