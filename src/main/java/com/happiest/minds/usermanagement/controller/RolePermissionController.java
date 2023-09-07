package com.happiest.minds.usermanagement.controller;

import com.happiest.minds.usermanagement.entity.RolePermission;
import com.happiest.minds.usermanagement.request.RolePermissionDTO;
import com.happiest.minds.usermanagement.service.RolePermissionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.happiest.minds.usermanagement.enums.Constants.*;

@RestController
@RequestMapping("api/user/management/role-permission")
@Slf4j
public class RolePermissionController {

    @Autowired
    RolePermissionService rolePermissionService;

    @PostMapping
    public ResponseEntity<RolePermission> addPermissionToRole(@Valid @RequestBody RolePermissionDTO rolePermissionDTO) {
        log.info(ROLE_PERMISSION_DTO.getValue(), rolePermissionDTO);
        RolePermission rolePermission = new RolePermission();
        BeanUtils.copyProperties(rolePermissionDTO, rolePermission);
        rolePermission = rolePermissionService.addPermissionToRole(rolePermission);
        log.info(ROLE_PERMISSION.getValue(), rolePermission);
        return new ResponseEntity<>(rolePermission, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deletePermissionOfRole(@Valid @RequestBody RolePermissionDTO rolePermissionDTO) {
        log.info(ROLE_PERMISSION_DTO.getValue(), rolePermissionDTO);
        RolePermission rolePermission = new RolePermission();
        BeanUtils.copyProperties(rolePermissionDTO, rolePermission);
        if (rolePermissionService.getByRoleIdAndPermissionId(rolePermission) != null) {
            rolePermissionService.deleteByRoleIdAndPermissionId(rolePermission);
            log.info(RECORD_DELETED_FOR_ID.getValue().concat(rolePermission.toString()));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.error(NO_DATA_FOUND.getValue().concat(rolePermissionDTO.toString()));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
