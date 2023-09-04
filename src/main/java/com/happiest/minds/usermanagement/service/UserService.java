package com.happiest.minds.usermanagement.service;

import com.happiest.minds.usermanagement.entity.User;
import com.happiest.minds.usermanagement.exception.NotFoundException;
import com.happiest.minds.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.happiest.minds.usermanagement.enums.Constants.*;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepo;

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    public User createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public User getUserById(Integer userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException(NO_DATA_FOUND.getValue() + userId));
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public void deleteUserById(Integer userId) {
        userRepo.deleteById(userId);
    }

}
