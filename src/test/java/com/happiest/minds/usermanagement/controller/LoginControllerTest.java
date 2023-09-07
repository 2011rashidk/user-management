package com.happiest.minds.usermanagement.controller;

import com.happiest.minds.usermanagement.request.LoginDTO;
import com.happiest.minds.usermanagement.response.LoginResponse;
import com.happiest.minds.usermanagement.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LoginControllerTest {
    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUserLogin() throws Exception {
        LoginDTO loginDTO = new LoginDTO();

        LoginResponse loginResponse = new LoginResponse();

        when(loginService.authenticate(loginDTO)).thenReturn(loginResponse);

        ResponseEntity<LoginResponse> response = loginController.authenticate(loginDTO);

        verify(loginService).authenticate(loginDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(loginResponse, response.getBody());
    }

}
