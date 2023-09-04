package com.happiest.minds.usermanagement.controller;

import com.happiest.minds.usermanagement.request.UserDTO;
import com.happiest.minds.usermanagement.response.LoginResponse;
import com.happiest.minds.usermanagement.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/management/registration")
@Slf4j
@RequiredArgsConstructor
public class RegistrationController {

    private final LoginService service;

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(service.register(userDTO));
    }

}
