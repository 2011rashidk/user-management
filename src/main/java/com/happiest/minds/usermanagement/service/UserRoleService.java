package com.happiest.minds.usermanagement.service;

import com.happiest.minds.usermanagement.entity.UserRole;
import com.happiest.minds.usermanagement.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

    @Autowired
    UserRoleRepository userRoleRepository;

    public UserRole addRoleToUser(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    public UserRole getByUserIdAndRoleId(UserRole userRole) {
        return userRoleRepository.findByUserIdAndRoleId(userRole.getUserId(), userRole.getRoleId());
    }

    public void deleteByUserIdAndRoleId(UserRole userRole) {
        userRoleRepository.deleteByUserIdAndRoleId(userRole.getUserId(), userRole.getRoleId());
    }

}
