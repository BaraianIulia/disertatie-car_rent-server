package com.disertatie.rent.car.examples;

import com.disertatie.rent.car.entities.User;
import com.disertatie.rent.car.model.enumType.UserRoleType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ExampleTests {

    @Spy
    List<String> spiedList = new ArrayList<String>();

    //@Spy example
    @Test
    public void userListSpy() {
        List<User> spyList = Mockito.spy(new ArrayList<>());

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

        spyList.add(user);
        spyList.add(user1);

        Mockito.verify(spyList).add(user);
        Mockito.verify(spyList).add(user1);

        assertEquals(2, spyList.size());

        Mockito.doReturn(100).when(spyList).size();
        assertEquals(100, spyList.size());

    }

    //@Captor Example
    @Mock
    List mockedList;

    @Captor
    ArgumentCaptor argCaptor;

    @Test
    public void whenUseCaptorAnnotation_thenTheSam() {
        mockedList.add("one");
        Mockito.verify(mockedList).add(argCaptor.capture());

        assertEquals("one", argCaptor.getValue());
    }
}
