package com.happiest.minds.usermanagement.controller;

import com.happiest.minds.usermanagement.request.UserDTO;
import com.happiest.minds.usermanagement.response.LoginResponse;
import com.happiest.minds.usermanagement.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.happiest.minds.usermanagement.enums.Constants.*;

@RestController
@RequestMapping("api/user/management/registration")
@Slf4j
public class RegistrationController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody UserDTO userDTO) {
        log.info(USER_DTO.getValue(), userDTO);
        LoginResponse loginResponse = loginService.register(userDTO);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

}
