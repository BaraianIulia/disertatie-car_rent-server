package com.disertatie.rent.car.transformers;

import com.disertatie.rent.car.entities.User;
import com.disertatie.rent.car.model.UserModel;
import com.disertatie.rent.car.model.enumType.UserRoleEnum;
import com.disertatie.rent.car.service.passwordEncoder.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@Component(value = "transformer")
public class Transformer {

    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;

    public User transformModelToEntity(UserModel userModel) {
        User user = new User();
        if (null != userModel.getId())
            user.setId(userModel.getId());
        user.setName(userModel.getName());
        user.setSurname(userModel.getSurname());
        user.setBirthdate(java.sql.Date.valueOf(userModel.getBirthdate()));
        user.setEmail(userModel.getEmail());
        user.setPassword(passwordEncoder.encoder(userModel.getPassword()));
        user.setPhone(userModel.getPhone());
        user.setAddress(userModel.getAddress());
        user.setPhoto(userModel.getPhoto().getBytes());
        if (userModel.getUserRole().toLowerCase().contains("user")) {
            user.setUserRole(UserRoleEnum.USER_ROLE);
        } else if (userModel.getUserRole().toLowerCase().contains("admin")) {
            user.setUserRole(UserRoleEnum.ADMIN_ROLE);
        }

        return user;
    }

    public UserModel transformEntityToModel(User user) {
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setName(user.getName());
        userModel.setSurname(user.getSurname());
        userModel.setBirthdate(user.getBirthdate().toLocalDate());
        userModel.setEmail(user.getEmail());
        userModel.setPhone(user.getPhone());
        userModel.setAddress(user.getAddress());
        userModel.setPhoto(new String(user.getPhoto(), StandardCharsets.UTF_8));
        userModel.setUserRole(user.getUserRole().toString());

        return userModel;

    }


}
