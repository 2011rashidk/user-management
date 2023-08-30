package com.happiest.minds.usermanagement.repository;

import com.happiest.minds.usermanagement.entity.UserRole;
import com.happiest.minds.usermanagement.id.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
    UserRole findByUserIdAndRoleId(Integer userId, Integer roleId);

    @Transactional
    void deleteByUserIdAndRoleId(Integer userId, Integer roleId);
}
