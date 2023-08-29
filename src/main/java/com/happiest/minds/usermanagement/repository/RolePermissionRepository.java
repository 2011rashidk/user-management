package com.happiest.minds.usermanagement.repository;

import com.happiest.minds.usermanagement.entity.RolePermission;
import com.happiest.minds.usermanagement.id.RolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionId> {
}
