package com.disertatie.rent.car.service.impl;

import com.disertatie.rent.car.entities.User;
import com.disertatie.rent.car.exceptions.*;
import com.disertatie.rent.car.model.UserModel;
import com.disertatie.rent.car.model.enumType.UserRoleType;
import com.disertatie.rent.car.repository.UserRepository;
import com.disertatie.rent.car.service.UserService;
import com.disertatie.rent.car.service.passwordEncoder.PasswordEncoder;
import com.disertatie.rent.car.transformers.Transformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
@Transactional
public class UserServiceImp implements UserService {

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Resource(name = "transformer")
    private Transformer transformer;


    @Override
    public List<UserModel> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserModel> userModelList = new ArrayList<>();
        for (User user : userList
        ) {
            userModelList.add(transformer.transformEntityToModel(user));
        }
        return userModelList;
    }

    @Override
    public UserModel login(String email, String password) throws ExceptionInvalidCredentials, ExceptionNotFound, ExceptionDeactivatedAccount {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
             if (passwordEncoder.check(password, user.getPassword())) {
                 if(user.isStatus() == false){
                     throw new ExceptionDeactivatedAccount("This account is deactivated.");
                 }
                return transformer.transformEntityToModel(user);
            } else {
                throw new ExceptionInvalidCredentials("Incorrect email or password.");
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
    public UserModel changeRole(Long userId, UserRoleType userRole, Long currentUserId) throws ExceptionNotFound, ExceptionUnauthorizedAction {
        Optional<User> optionalUser = userRepository.findById(currentUserId);
        if (!optionalUser.isPresent()) {
            throw new ExceptionNotFound("User with id: " + currentUserId + " does not exist.");
        } else if (optionalUser.get().getUserRole().equals(UserRoleType.ADMIN_ROLE)) {
            Optional<User> optionalUserToChange = userRepository.findById(userId);
            if (!optionalUserToChange.isPresent()) {
                throw new ExceptionNotFound("User with id: " + currentUserId + " does not exist.");
            } else {
                User userToChange = optionalUserToChange.get();
                userToChange.setUserRole(userRole);
                return transformer.transformEntityToModel(userRepository.save(userToChange));
            }
        } else {
            throw new ExceptionUnauthorizedAction("You are not allowed to make this change");
        }
    }

    @Override
    public UserModel changeStatus(Long userId, boolean status, Long currentUserId) throws ExceptionNotFound, ExceptionUnauthorizedAction {
        Optional<User> optionalUser = userRepository.findById(currentUserId);
        if (!optionalUser.isPresent()) {
            throw new ExceptionNotFound("User with id: " + currentUserId + " does not exist.");
        } else if (optionalUser.get().getUserRole().equals(UserRoleType.ADMIN_ROLE)) {
            Optional<User> optionalUserToChange = userRepository.findById(userId);
            if (!optionalUserToChange.isPresent()) {
                throw new ExceptionNotFound("User with id: " + currentUserId + " does not exist.");
            } else {
                User userToChange = optionalUserToChange.get();
                userToChange.setStatus(status);
                return transformer.transformEntityToModel(userRepository.save(userToChange));
            }
        } else {
            throw new ExceptionUnauthorizedAction("You are not allowed to make this change");
        }
    }

    @Override
    public UserModel editProfileData(UserModel userModel) throws ExceptionNotFound {
        Optional<User> optionalUser = userRepository.findById(userModel.getId());
        if (!optionalUser.isPresent()) {
            throw new ExceptionNotFound("User with email: " + userModel.getEmail() + " does not exist.");
        } else {
            User user = optionalUser.get();
            user.setName(userModel.getName());
            user.setSurname(userModel.getSurname());
            user.setEmail(userModel.getEmail());
            user.setPhone(userModel.getPhone());
            user.setAddress(userModel.getAddress());
            return transformer.transformEntityToModel(userRepository.save(user));
        }
    }

    @Override
    public void changePassword(Long userId, String actualPassword, String newPassword) throws ExceptionInvalidCredentials, ExceptionNotFound {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new ExceptionNotFound("User with id: " + userId + " does not exist.");
        }
        User user = optionalUser.get();
        if (passwordEncoder.check(actualPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encoder(newPassword));
            userRepository.save(user);
        }else {
            throw new ExceptionInvalidCredentials("The current password does not match.");
        }
    }

    @Override
    public UserModel getUserById(Long userId) throws ExceptionNotFound {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new ExceptionNotFound("User with id: " + userId + " does not exist.");
        }
        return transformer.transformEntityToModel(optionalUser.get());
    }

    @Override
    public void changeProfilePicture(Long userId, String photo) throws ExceptionNotFound, ExceptionInvalidData {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new ExceptionNotFound("User with id: " + userId + " does not exist.");
        }
        User user = optionalUser.get();
        if (!StringUtils.isEmpty(photo)) {
            user.setPhoto(photo.getBytes());
            userRepository.save(user);
        }else{
            throw new ExceptionInvalidData("You can not set an empty picture.");
        }
    }

}
