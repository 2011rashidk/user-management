package com.happiest.minds.usermanagement.repository;

import com.happiest.minds.usermanagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>  {
}
