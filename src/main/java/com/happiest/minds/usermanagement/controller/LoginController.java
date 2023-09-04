package com.happiest.minds.usermanagement.controller;

import com.happiest.minds.usermanagement.request.LoginDTO;
import com.happiest.minds.usermanagement.response.LoginResponse;
import com.happiest.minds.usermanagement.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/user/management/login")
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private LoginService service;


    @PostMapping
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginDTO loginDTO) {
        return new ResponseEntity<>(service.authenticate(loginDTO), HttpStatus.OK);
    }

    @PostMapping("refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }

}