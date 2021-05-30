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
        user.setId(1L);
        user.setName("test_name");
        user.setEmail("test@yahoo.com");
        user.setUserRole(UserRoleType.USER_ROLE);
        user.setPassword("$2a$10$IbD0qE2Imo0VZ6qQm6.X8OZi.YHposIEU397QVYg.U4V/o6.gsoCO");
        user.setPhone("0765787876");
        user.setAddress("str. Mare");
        user.setStatus(true);
        return user;
    }

    public static User aUser1(){
        User user = new User();
        user.setName("test_name");
        user.setEmail("test@yahoo.com");
        user.setUserRole(UserRoleType.USER_ROLE);
        user.setPassword("$2a$10$IbD0qE2Imo0VZ6qQm6.X8OZi.YHposIEU397QVYg.U4V/o6.gsoCO");
        user.setPhone("0765787876");
        user.setAddress("str. Mare");
        user.setStatus(false);
        return user;
    }

    public static User aUser2(){
        User user = new User();
        user.setName("test1_name");
        user.setEmail("test1@yahoo.com");
        user.setUserRole(UserRoleType.USER_ROLE);
        user.setPassword("test12");
        user.setPhone("0765787176");
        user.setAddress("str. Mare1");
        user.setStatus(false);
        return user;
    }

    public static UserModel aUserModel(){
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setName("test_name");
        userModel.setEmail("test@yahoo.com");
        userModel.setUserRole(UserRoleType.USER_ROLE.toString());
        userModel.setPassword("fff");
        userModel.setPhone("0765787876");
        userModel.setAddress("str. Mare");
        return userModel;
    }

    public static UserModel aUserModel1(){
        UserModel userModel = new UserModel();
        userModel.setName("test_name");
        userModel.setEmail("test@yahoo.com");
        userModel.setUserRole(UserRoleType.USER_ROLE.toString());
        userModel.setPassword("test");
        userModel.setPhone("0765787876");
        userModel.setAddress("str. Mare");
        userModel.setStatus(false);
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
