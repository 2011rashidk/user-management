package com.happiest.minds.usermanagement.controller;

import com.happiest.minds.usermanagement.dto.PermissionDTO;
import com.happiest.minds.usermanagement.entity.Permission;
import com.happiest.minds.usermanagement.service.PermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user/management/permission")
public class PermissionController {
    @Autowired
    public PermissionService permissionService;

    @PostMapping
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission) {
        permissionService.createPermission(permission);
        return new ResponseEntity<>(permission, HttpStatus.CREATED);
    }

    @GetMapping("{permissionId}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable Integer permissionId) {
        Permission permissionById = permissionService.getPermissionById(permissionId);
        return new ResponseEntity<>(permissionById, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Permission>> getAllPermissions() {
        List<Permission> permissions = permissionService.getAllPermissions();
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

    @PutMapping("{permissionId}")
    public ResponseEntity<Permission> updatePermissionById(@PathVariable Integer permissionId,
                                                           @RequestBody PermissionDTO permissionDTO) {
        if (permissionService.getPermissionById(permissionId) != null) {
            Permission permission = new Permission();
            BeanUtils.copyProperties(permissionDTO, permission);
            permission.setPermissionId(permissionId);
            permission = permissionService.createPermission(permission);
            return new ResponseEntity<>(permission, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("{permissionId}")
    public ResponseEntity<HttpStatus> deletePermission(@PathVariable Integer permissionId) {
        permissionService.deletePermission(permissionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
