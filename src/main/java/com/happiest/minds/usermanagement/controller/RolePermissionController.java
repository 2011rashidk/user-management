package com.happiest.minds.usermanagement.controller;

import com.happiest.minds.usermanagement.dto.RolePermissionDTO;
import com.happiest.minds.usermanagement.entity.RolePermission;
import com.happiest.minds.usermanagement.service.RolePermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/management/role/permission")
public class RolePermissionController {

    @Autowired
    RolePermissionService rolePermissionService;

    @PostMapping
    public ResponseEntity<RolePermission> addPermissionToRole(@RequestBody RolePermissionDTO rolePermissionDTO) {
        RolePermission rolePermission = new RolePermission();
        BeanUtils.copyProperties(rolePermissionDTO, rolePermission);
        rolePermission = rolePermissionService.addPermissionToRole(rolePermission);
        return new ResponseEntity<>(rolePermission, HttpStatus.OK);
    }

}
