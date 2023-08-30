package com.happiest.minds.usermanagement.repository;

import com.happiest.minds.usermanagement.entity.RolePermission;
import com.happiest.minds.usermanagement.id.RolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionId> {
    RolePermission getByRoleIdAndPermissionId(Integer roleId, Integer permissionId);


    @Transactional
    void deleteByRoleIdAndPermissionId(Integer roleId, Integer permissionId);
}
