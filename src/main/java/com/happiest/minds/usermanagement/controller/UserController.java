package com.happiest.minds.usermanagement.controller;

import com.happiest.minds.usermanagement.entity.User;
import com.happiest.minds.usermanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.happiest.minds.usermanagement.enums.Constants.*;

@RestController
@RequestMapping("api/user/management/user")
@Slf4j
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        log.info(USER_INPUT.getValue(), user);
        user = userService.createUser(user);
        log.info(USER.getValue(), user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("{userId}")
    public ResponseEntity<User> getUserById(@Valid @NonNull @PathVariable Integer userId) {
        log.info(ID.getValue(), userId);
        User user = userService.getUserById(userId);
        log.info(USER.getValue(), user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        log.info(USER.getValue(), users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("{userId}")
    public ResponseEntity<User> updateUserById(@Valid @NonNull @PathVariable Integer userId,
                                               @Valid @RequestBody User user) {
        log.info(ID.getValue(), userId);
        log.info(USER_INPUT.getValue(), user);
        if (userService.getUserById(userId) != null) {
            user.setUserId(userId);
            user = userService.createUser(user);
            log.info(USER.getValue(), user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        log.error(NO_DATA_FOUND.getValue().concat(userId.toString()));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<HttpStatus> deleteUserById(@Valid @NonNull @PathVariable Integer userId) {
        log.info(ID.getValue(), userId);
        if (userService.getUserById(userId) != null) {
            userService.deleteUserById(userId);
            log.info(RECORD_DELETED_FOR_ID.getValue().concat(userId.toString()));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.error(NO_DATA_FOUND.getValue().concat(userId.toString()));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
	

