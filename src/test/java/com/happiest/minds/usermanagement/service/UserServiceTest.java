package com.happiest.minds.usermanagement.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.happiest.minds.usermanagement.entity.User;
import com.happiest.minds.usermanagement.exception.NotFoundException;
import com.happiest.minds.usermanagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        User user = new User();
        String encodedPassword = "encoded_password";
        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn(encodedPassword);
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.createUser(user);

        assertEquals(user, result);
        assertEquals(encodedPassword, user.getPassword());
        verify(userRepository).save(user);
    }


    @Test
    void testGetUserById_ExistingUser() {
        Integer userId = 1;
        User user = new User();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = userService.getUserById(userId);

        assertEquals(user, result);
        verify(userRepository).findById(userId);
    }

    @Test
    void testGetUserById_NonExistingUser() {
        Integer userId = 1;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            userService.getUserById(userId);
        });
        verify(userRepository).findById(userId);
    }

    @Test
    void testGetAllUsers() {
        List<User> users = new ArrayList<>();

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(users, result);
        verify(userRepository).findAll();
    }

    @Test
    void testDeleteUserById() {
        Integer userId = 1;

        userService.deleteUserById(userId);

        verify(userRepository).deleteById(userId);
    }

}