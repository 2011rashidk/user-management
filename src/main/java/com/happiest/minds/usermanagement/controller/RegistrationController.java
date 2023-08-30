package com.happiest.minds.usermanagement.controller;

import com.happiest.minds.usermanagement.request.UserDTO;
import com.happiest.minds.usermanagement.entity.User;
import com.happiest.minds.usermanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/management/registration")
@Slf4j
public class RegistrationController {

    @Autowired
    public UserService userService;

    @PostMapping("register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserDTO userDTO) {
        log.info("userDTO: {}", userDTO);
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user = userService.createUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
