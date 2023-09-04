package com.happiest.minds.usermanagement.service;

import com.happiest.minds.usermanagement.entity.Permission;
import com.happiest.minds.usermanagement.exception.NotFoundException;
import com.happiest.minds.usermanagement.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.happiest.minds.usermanagement.enums.Constants.*;

@Service
public class PermissionService {
    @Autowired
    public PermissionRepository permissionRepo;

    public Permission createPermission(Permission permission) {
        permissionRepo.save(permission);
        return permission;
    }

    public Permission getPermissionById(Integer permissionId) {
        return permissionRepo.findById(permissionId)
                .orElseThrow(() -> new NotFoundException(NO_DATA_FOUND.getValue() + permissionId));
    }

    public List<Permission> getAllPermissions() {
        return permissionRepo.findAll();
    }

    public void deletePermission(Integer permissionId) {
        permissionRepo.deleteById(permissionId);
    }

}
