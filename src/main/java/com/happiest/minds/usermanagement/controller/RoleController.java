package com.happiest.minds.usermanagement.controller;

import com.happiest.minds.usermanagement.dto.RoleDTO;
import com.happiest.minds.usermanagement.entity.Role;
import com.happiest.minds.usermanagement.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user/management/role")
@Slf4j
public class RoleController {
    @Autowired
    public RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        role = roleService.createRole(role);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @GetMapping("{roleId}")
    public ResponseEntity<Role> getRoleById(@PathVariable Integer roleId) {
        Role roleById = roleService.getRoleById(roleId);
        return new ResponseEntity<>(roleById, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);

    }

    @PutMapping("{roleId}")
    public ResponseEntity<Role> updateRoleById(@PathVariable Integer roleId,
                                               @RequestBody Role role) {
        if (roleService.getRoleById(roleId) != null) {
            role.setRoleId(roleId);
            role = roleService.createRole(role);
            return new ResponseEntity<>(role, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{roleId}")
    public ResponseEntity<HttpStatus> deleteRole(@PathVariable Integer roleId) {
        roleService.deleteRole(roleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
