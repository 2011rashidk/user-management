package com.happiest.minds.usermanagement.controller;

import com.happiest.minds.usermanagement.entity.User;
import com.happiest.minds.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user/management/user")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user = userService.createUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("{userId}")
    public ResponseEntity<User> updateUserById(@PathVariable Integer userId,
                                               @RequestBody User user) {
        if (userService.getUserById(userId) != null) {
            user.setUserId(userId);
            user = userService.createUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable Integer userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
	

