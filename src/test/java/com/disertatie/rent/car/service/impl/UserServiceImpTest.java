package com.disertatie.rent.car.service.impl;

import com.disertatie.rent.car.entities.User;
import com.disertatie.rent.car.exceptions.ExceptionDeactivatedAccount;
import com.disertatie.rent.car.exceptions.ExceptionExistingUser;
import com.disertatie.rent.car.exceptions.ExceptionInvalidCredentials;
import com.disertatie.rent.car.exceptions.ExceptionNotFound;
import com.disertatie.rent.car.model.UserModel;
import com.disertatie.rent.car.repository.UserRepository;
import com.disertatie.rent.car.service.passwordEncoder.PasswordEncoder;
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
    @Mock
    private PasswordEncoder passwordEncoder;

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
        when(userRepository.findByEmail(aUserModel().getEmail())).thenReturn(Optional.ofNullable(aUser()));
        when(passwordEncoder.check(aUserModel().getPassword(), aUser().getPassword())).thenReturn(true);

        try {
            userService.login(aUserModel().getEmail(), aUserModel().getPassword());
        } catch (ExceptionInvalidCredentials | ExceptionDeactivatedAccount | ExceptionNotFound exceptionInvalidCredentials) {
            exceptionInvalidCredentials.printStackTrace();
        }
    }

    @Test
    public void loginDeactivatedAccount()  {

    }

    @Test(expected = ExceptionInvalidCredentials.class)
    public void loginInvalidCredentials() throws ExceptionInvalidCredentials, ExceptionNotFound, ExceptionDeactivatedAccount {
        when(userRepository.findByEmail(aUserModel().getEmail())).thenReturn(Optional.ofNullable(aUser()));

        userService.login(aUserModel().getEmail(), "abc");

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
    public void getUserById() throws ExceptionNotFound {
        when(userRepository.findById(aUserModel().getId())).thenReturn(Optional.ofNullable(aUser()));
        when(transformer.transformEntityToModel((User) any())).thenReturn(aUserModel());

        UserModel userModel = userService.getUserById(aUserModel().getId());

        assertEquals(userModel.getId(), aUser().getId());
    }

    @Test(expected = ExceptionNotFound.class)
    public void getUserByIdException() throws ExceptionNotFound {
        when(userRepository.findById(aUserModel().getId())).thenReturn(Optional.empty());
        when(transformer.transformEntityToModel((User) any())).thenReturn(aUserModel());

        UserModel userModel = userService.getUserById(aUserModel().getId());

        assertEquals(userModel.getId(), aUser().getId());
    }

    @Test
    public void changeProfilePicture() {
    }
}