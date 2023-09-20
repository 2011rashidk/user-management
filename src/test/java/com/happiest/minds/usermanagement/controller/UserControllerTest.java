package com.happiest.minds.usermanagement.controller;

import com.happiest.minds.usermanagement.entity.User;
import com.happiest.minds.usermanagement.request.UserDTO;
import com.happiest.minds.usermanagement.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        User user = new User();
        UserDTO userDTO = new UserDTO();

        when(userService.createUser(user)).thenReturn(user);

        ResponseEntity<UserDTO> response = userController.createUser(user);

        verify(userService).createUser(user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
    }
    @Test
    void testGetUserById() {
        Integer userId = 123;
        User user = new User();

        when(userService.getUserById(userId)).thenReturn(user);

        ResponseEntity<User> response = userController.getUserById(userId);

        verify(userService).getUserById(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testGetAllUsers() {
        List<User> users = new ArrayList<>();

        when(userService.getAllUsers()).thenReturn(users);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        verify(userService).getAllUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    void testUpdateUserById_ExistingUser() {
        Integer userId = 123;
        User user = new User();
        UserDTO userDTO = new UserDTO();

        User existingUser = new User();

        when(userService.getUserById(userId)).thenReturn(existingUser);
        when(userService.createUser(user)).thenReturn(user);

        ResponseEntity<UserDTO> response = userController.updateUserById(userId, user);

        verify(userService).getUserById(userId);
        verify(userService).createUser(user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(userDTO, response.getBody());
    }

    @Test
    void testUpdateUserById_NonExistingUser() {
        Integer userId = 123;
        User user = new User();

        when(userService.getUserById(userId)).thenReturn(null);

        ResponseEntity<UserDTO> response = userController.updateUserById(userId, user);

        verify(userService).getUserById(userId);
        verify(userService, never()).createUser(any(User.class));
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
    @Test
    void testDeleteUserById_ExistingUser() {
        Integer userId = 123;

        when(userService.getUserById(userId)).thenReturn(new User());

        ResponseEntity<HttpStatus> response = userController.deleteUserById(userId);

        verify(userService).getUserById(userId);
        verify(userService).deleteUserById(userId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteUserById_NonExistingUser() {
        Integer userId = 123;

        when(userService.getUserById(userId)).thenReturn(null);

        ResponseEntity<HttpStatus> response = userController.deleteUserById(userId);

        verify(userService).getUserById(userId);
        verify(userService, never()).deleteUserById(anyInt());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

}