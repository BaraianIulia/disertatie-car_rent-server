package com.disertatie.rent.car.prototype;

import com.disertatie.rent.car.entities.User;
import com.disertatie.rent.car.model.UserModel;
import com.disertatie.rent.car.model.enumType.UserRoleType;
import java.util.List;


import java.util.ArrayList;
import java.util.Date;

public class UserPrototype {

    public static User aUser(){
        User user = new User();
        user.setName("test_name");
        user.setEmail("test@yahoo.com");
        user.setUserRole(UserRoleType.USER_ROLE);
        user.setPassword("test");
        user.setPhone("0765787876");
        user.setAddress("str. Mare");
        return user;
    }

    public static User aUser1(){
        User user = new User();
        user.setName("test1_name");
        user.setEmail("test1@yahoo.com");
        user.setUserRole(UserRoleType.USER_ROLE);
        user.setPassword("test1");
        user.setPhone("0765787176");
        user.setAddress("str. Mare1");
        return user;
    }

    public static UserModel aUserModel(){
        UserModel userModel = new UserModel();
        userModel.setName("test_name");
        userModel.setEmail("test@yahoo.com");
        userModel.setUserRole(UserRoleType.USER_ROLE.toString());
        userModel.setPassword("test");
        userModel.setPhone("0765787876");
        userModel.setAddress("str. Mare");
        return userModel;
    }

    public static UserModel aUserModel1(){
        UserModel userModel = new UserModel();
        userModel.setName("test1_name");
        userModel.setEmail("test1@yahoo.com");
        userModel.setUserRole(UserRoleType.USER_ROLE.toString());
        userModel.setPassword("test1");
        userModel.setPhone("0765787176");
        userModel.setAddress("str. Mare1");
        return userModel;
    }

    public static List<User> aUserList(){
        User user = new User();
        user.setName("test_name");
        user.setEmail("test@yahoo.com");
        user.setUserRole(UserRoleType.USER_ROLE);
        user.setPassword("test");
        user.setPhone("0765787876");
        user.setAddress("str. Mare");
        User user1 = new User();
        user.setName("test1_name");
        user.setEmail("test1@yahoo.com");
        user.setUserRole(UserRoleType.USER_ROLE);
        user.setPassword("test1");
        user.setPhone("0765787176");
        user.setAddress("str. Mare1");
        List< User> list = new ArrayList<>();
        list.add(user);
        list.add(user1);
        return list;
    }

}
