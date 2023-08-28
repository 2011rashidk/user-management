package com.happiest.minds.usermanagement.repository;

import com.happiest.minds.usermanagement.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Integer> {
}
