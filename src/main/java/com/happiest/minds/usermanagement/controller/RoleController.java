package com.happiest.minds.usermanagement.controller;

import com.happiest.minds.usermanagement.entity.Role;
import com.happiest.minds.usermanagement.service.RoleService;
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
@RequestMapping("api/user/management/role")
@Slf4j
public class RoleController {
    @Autowired
    public RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        log.info("Input request:: role: {}", role);
        role = roleService.createRole(role);
        log.info("role: {}", role);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @GetMapping("{roleId}")
    public ResponseEntity<Role> getRoleById(@Valid @NonNull @PathVariable Integer roleId) {
        log.info("roleId: {}", roleId);
        Role role = roleService.getRoleById(roleId);
        log.info("role: {}", role);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        log.info("roles: {}", roles);
        return new ResponseEntity<>(roles, HttpStatus.OK);

    }

    @PutMapping("{roleId}")
    public ResponseEntity<Role> updateRoleById(@Valid @NonNull @PathVariable Integer roleId,
                                               @Valid @RequestBody Role role) {
        log.info("Input request:: roleId: {}, role: {}", roleId, role);
        if (roleService.getRoleById(roleId) != null) {
            role.setRoleId(roleId);
            role = roleService.createRole(role);
            log.info("role: {}", role);
            return new ResponseEntity<>(role, HttpStatus.OK);
        }
        log.error(NO_DATA_FOUND.getValue().concat(roleId.toString()));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{roleId}")
    public ResponseEntity<HttpStatus> deleteRole(@Valid @NonNull @PathVariable Integer roleId) {
        log.info("roleId: {}", roleId);
        if (roleService.getRoleById(roleId) != null) {
            roleService.deleteRole(roleId);
            log.info(RECORD_DELETED_FOR_ID.getValue().concat(roleId.toString()));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.error(NO_DATA_FOUND.getValue().concat(roleId.toString()));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
