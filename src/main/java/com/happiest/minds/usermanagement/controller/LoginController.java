package com.happiest.minds.usermanagement.controller;

import com.happiest.minds.usermanagement.request.LoginDTO;
import com.happiest.minds.usermanagement.response.LoginResponse;
import com.happiest.minds.usermanagement.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.happiest.minds.usermanagement.enums.Constants.*;

@RestController
@RequestMapping("api/user/management/login")
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginDTO loginDTO) {
        log.info(LOGIN_DTO.getValue(), loginDTO);
        LoginResponse loginResponse = loginService.authenticate(loginDTO);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @PostMapping("refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info(REFRESH_TOKEN_CALLED.getValue());
        loginService.refreshToken(request, response);
    }

}