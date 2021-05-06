package com.disertatie.rent.car.service.impl;

import com.disertatie.rent.car.entities.User;
import com.disertatie.rent.car.exceptions.ExceptionExistingUser;
import com.disertatie.rent.car.model.UserModel;
import com.disertatie.rent.car.repository.UserRepository;
import com.disertatie.rent.car.transformers.Transformer;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static com.disertatie.rent.car.prototype.UserPrototype.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImpTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private Transformer transformer;

    @InjectMocks
    private UserServiceImp userService;

    @BeforeEach
    void beforeEach() {

    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this); //without this you will get NPE
    }

    @Test
    public void getAllUsers() {


        assertNotNull(userRepository);
        assertNotNull(transformer);
        when(userRepository.findAll()).thenReturn(aUserList());
        lenient().when(transformer.transformEntityToModel(aUserList().get(0))).thenReturn(aUserModel());
        lenient().when(transformer.transformEntityToModel(aUserList().get(1))).thenReturn(aUserModel());
        List<UserModel> createdUser = userService.getAllUsers();

        assertEquals(2, createdUser.size());

    }

    @Test
    public void login() {
    }

    @Test
    public void register() throws ExceptionExistingUser {
        assertNotNull(userRepository);
        assertNotNull(transformer);
        lenient().when(transformer.transformModelToEntity((aUserModel()))).thenReturn(aUser());
        lenient().when(userRepository.save(aUser())).thenReturn(aUser());
        lenient().when(transformer.transformEntityToModel((User) any())).thenReturn(aUserModel());
        lenient().when(userRepository.findByEmail(any())).thenReturn(Optional.empty());

        UserModel createdUser = userService.register(aUserModel());
        assertThat(createdUser.getName()).isNotNull();
        assertThat(createdUser.getName()).isEqualTo(aUserModel().getName());

    }

    @Test(expected = ExceptionExistingUser.class)
    public void registerExist() throws ExceptionExistingUser {
        assertNotNull(userRepository);
        assertNotNull(transformer);
        lenient().when(transformer.transformModelToEntity((aUserModel()))).thenReturn(aUser());
        lenient().when(userRepository.save(aUser())).thenReturn(aUser());
        lenient().when(transformer.transformEntityToModel((User) any())).thenReturn(aUserModel());
        lenient().when(userRepository.findByEmail(any())).thenReturn(Optional.ofNullable(aUser()));


        UserModel createdUser = userService.register(aUserModel());
        assertThat(createdUser.getName()).isNotNull();
        assertThat(createdUser.getName()).isEqualTo(aUserModel().getName());

    }

    @Test
    public void changeRole() {
    }

    @Test
    public void changeStatus() {
    }

    @Test
    public void editProfileData() {
    }

    @Test
    public void changePassword() {
    }

    @Test
    public void getUserById() {
    }

    @Test
    public void changeProfilePicture() {
    }
}