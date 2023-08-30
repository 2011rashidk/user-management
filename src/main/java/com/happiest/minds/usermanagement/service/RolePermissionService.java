package com.happiest.minds.usermanagement.service;

import com.happiest.minds.usermanagement.entity.RolePermission;
import com.happiest.minds.usermanagement.repository.RolePermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionService {

    @Autowired
    RolePermissionRepository rolePermissionRepository;

    public RolePermission addPermissionToRole(RolePermission rolePermission) {
        return rolePermissionRepository.save(rolePermission);
    }

    public RolePermission getByRoleIdAndPermissionId(RolePermission rolePermission) {
        return rolePermissionRepository.getByRoleIdAndPermissionId(rolePermission.getRoleId(), rolePermission.getPermissionId());
    }

    public void deleteByRoleIdAndPermissionId(RolePermission rolePermission) {
        rolePermissionRepository.deleteByRoleIdAndPermissionId(rolePermission.getRoleId(), rolePermission.getPermissionId());
    }

}
