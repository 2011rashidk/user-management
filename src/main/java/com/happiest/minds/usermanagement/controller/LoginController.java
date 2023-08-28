package com.happiest.minds.usermanagement.controller;

import com.happiest.minds.usermanagement.dto.LoginResponse;
import com.happiest.minds.usermanagement.dto.TokenRefreshResponse;
import com.happiest.minds.usermanagement.dto.UserLogin;
import com.happiest.minds.usermanagement.service.JwtAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user/management/login")
public class LoginController {

    @Autowired
    public JwtAuthenticationService jwtAuthenticationService;

    @PostMapping
    public ResponseEntity<?> userLogin(@RequestBody UserLogin userLogin) throws Exception {
        LoginResponse response = jwtAuthenticationService.createAuthenticationToken(userLogin);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("refresh/token")
    public ResponseEntity<TokenRefreshResponse> refreshToken(HttpServletRequest request) throws Exception {
        String token = jwtAuthenticationService.refreshToken(request);
        return new ResponseEntity<>(new TokenRefreshResponse(token), HttpStatus.OK);
    }

}