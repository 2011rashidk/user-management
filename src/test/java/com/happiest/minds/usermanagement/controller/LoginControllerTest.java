//package com.happiest.minds.usermanagement.controller;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class LoginControllerTest {
//    @Mock
//    private JwtAuthenticationService jwtAuthenticationService;
//
//    @InjectMocks
//    private LoginController loginController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testUserLogin() throws Exception {
//        LoginDTO loginDTO = new LoginDTO();
//
//        LoginResponse loginResponse = new LoginResponse();
//
//        when(jwtAuthenticationService.createAuthenticationToken(loginDTO)).thenReturn(loginResponse);
//
//        ResponseEntity<LoginResponse> response = loginController.userLogin(loginDTO);
//
//        verify(jwtAuthenticationService).createAuthenticationToken(loginDTO);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(loginResponse, response.getBody());
//    }
//
//}
