package com.exam.cash.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.exam.cash.model.User;
import com.exam.cash.repositories.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
 
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @Test
    public void shouldGetAllUsers() {

        List<User> users = new ArrayList<>();
        users.add(new User(1l, "email", "name1", "surname1"));
        users.add(new User(2l,"email2", "name2", "surname2"));
        users.add(new User(3l,"email3", "name3", "surname3"));

        when(userRepository.findAll()).thenReturn(users);

        assertEquals(users, userService.getAllUsers());
    }

    @Test
    public void shouldGetOneUser() {

        List<User> users = new ArrayList<>();
        users.add(new User(1l, "email", "name1", "surname1"));

        when(userRepository.findAll()).thenReturn(users);

        assertEquals(users, userService.getAllUsers());
    }

    @Test
    public void shouldGetSpecifiedUser() {

        User testUser = new User(1l, "email", "name1", "surname1");

        when(userRepository.findById(1l)).thenReturn(Optional.of(testUser));

        assertEquals(userService.getUser(1l), Optional.of(testUser));
    }

    @Test
    public void specifiedUserDoesNotExist() {

        when(userRepository.findById(1l)).thenReturn(Optional.empty());

        assertEquals(userService.getUser(1l), Optional.empty());
    }

    @Test
    public void shouldSaveUser() {

        User testUser = new User(1l, "email", "name1", "surname1");

        when(userRepository.findByEmail(testUser.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(testUser)).thenReturn(testUser);

        assertEquals(userService.save(testUser), Optional.of(testUser));
    }
    
    @Test
    public void userEmailAlreadyRegistered() {

        User testUser = new User(1l, "email", "name1", "surname1");

        when(userRepository.findByEmail(testUser.getEmail())).thenReturn(Optional.of(testUser));

        assertEquals(userService.save(testUser), Optional.empty());
    }

}
