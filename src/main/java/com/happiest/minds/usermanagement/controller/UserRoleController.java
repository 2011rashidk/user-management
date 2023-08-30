package com.happiest.minds.usermanagement.controller;

import com.happiest.minds.usermanagement.request.UserRoleDTO;
import com.happiest.minds.usermanagement.entity.UserRole;
import com.happiest.minds.usermanagement.service.UserRoleService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.happiest.minds.usermanagement.enums.Constants.*;

@RestController
@RequestMapping("api/user/management/user/role")
@Slf4j
public class UserRoleController {

    @Autowired
    UserRoleService userRoleService;

    @PostMapping
    public ResponseEntity<UserRole> addRoleToUser(@Valid @RequestBody UserRoleDTO userRoleDTO) {
        log.info("userRoleDTO: {}", userRoleDTO);
        UserRole userRole = new UserRole();
        BeanUtils.copyProperties(userRoleDTO, userRole);
        userRole = userRoleService.addRoleToUser(userRole);
        log.info("userRole: {}", userRole);
        return new ResponseEntity<>(userRole, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteRoleOfUser(@Valid @RequestBody UserRoleDTO userRoleDTO) {
        log.info("userRoleDTO: {}", userRoleDTO);
        UserRole userRole = new UserRole();
        BeanUtils.copyProperties(userRoleDTO, userRole);
        if (userRoleService.getByUserIdAndRoleId(userRole) != null) {
            userRoleService.deleteByUserIdAndRoleId(userRole);
            log.info(RECORD_DELETED_FOR_ID.getValue().concat(userRole.toString()));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.error(NO_DATA_FOUND.getValue().concat(userRoleDTO.toString()));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
