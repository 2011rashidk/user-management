package com.happiest.minds.usermanagement.controller;

import com.happiest.minds.usermanagement.dto.UserRoleDTO;
import com.happiest.minds.usermanagement.entity.UserRole;
import com.happiest.minds.usermanagement.service.UserRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/management/user/role")
public class UserRoleController {

    @Autowired
    UserRoleService userRoleService;

    @PostMapping
    public ResponseEntity<UserRole> addRoleToUser(@RequestBody UserRoleDTO userRoleDTO) {
        UserRole userRole = new UserRole();
        BeanUtils.copyProperties(userRoleDTO, userRole);
        userRoleService.addRoleToUser(userRole);
        return new ResponseEntity<>(userRole, HttpStatus.OK);
    }
}
