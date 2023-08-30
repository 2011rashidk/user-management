package com.happiest.minds.usermanagement.controller;

import com.happiest.minds.usermanagement.request.PermissionDTO;
import com.happiest.minds.usermanagement.entity.Permission;
import com.happiest.minds.usermanagement.service.PermissionService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.happiest.minds.usermanagement.enums.Constants.*;

@RestController
@RequestMapping("api/user/management/permission")
@Slf4j
public class PermissionController {
    @Autowired
    public PermissionService permissionService;

    @PostMapping
    public ResponseEntity<Permission> createPermission(@Valid @RequestBody PermissionDTO permissionDTO) {
        log.info("permissionDTO: {}", permissionDTO);
        Permission permission = new Permission();
        BeanUtils.copyProperties(permissionDTO, permission);
        permission = permissionService.createPermission(permission);
        log.info("permission: {}", permission);
        return new ResponseEntity<>(permission, HttpStatus.CREATED);
    }

    @GetMapping("{permissionId}")
    public ResponseEntity<Permission> getPermissionById(@Valid @NonNull @PathVariable Integer permissionId) {
        log.info("permissionId: {}", permissionId);
        Permission permission = permissionService.getPermissionById(permissionId);
        log.info("permission: {}", permission);
        return new ResponseEntity<>(permission, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Permission>> getAllPermissions() {
        List<Permission> permissions = permissionService.getAllPermissions();
        log.info("permissions: {}", permissions);
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

    @PutMapping("{permissionId}")
    public ResponseEntity<Permission> updatePermissionById(@Valid @NonNull @PathVariable Integer permissionId,
                                                           @Valid @RequestBody PermissionDTO permissionDTO) {
        log.info("permissionId: {}, permissionDTO: {}", permissionId, permissionDTO);
        if (permissionService.getPermissionById(permissionId) != null) {
            Permission permission = new Permission();
            BeanUtils.copyProperties(permissionDTO, permission);
            permission.setPermissionId(permissionId);
            permission = permissionService.createPermission(permission);
            log.info("permission: {}", permission);
            return new ResponseEntity<>(permission, HttpStatus.OK);
        }
        log.error(NO_DATA_FOUND.getValue().concat(permissionId.toString()));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{permissionId}")
    public ResponseEntity<HttpStatus> deletePermission(@Valid @NonNull @PathVariable Integer permissionId) {
        log.info("permissionId: {}", permissionId);
        if (permissionService.getPermissionById(permissionId) != null) {
            permissionService.deletePermission(permissionId);
            log.info(RECORD_DELETED_FOR_ID.getValue().concat(permissionId.toString()));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.error(NO_DATA_FOUND.getValue().concat(permissionId.toString()));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
