package com.happiest.minds.usermanagement.service;

import com.happiest.minds.usermanagement.entity.Role;
import com.happiest.minds.usermanagement.exception.ResourceNotFoundException;
import com.happiest.minds.usermanagement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    public RoleRepository roleRepository;

    public Role createRole(Role role) {

        return roleRepository.save(role);
    }

    public Role getRoleById(Integer roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found role with id" + roleId));
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public void deleteRole(Integer roleId) {
        roleRepository.deleteById(roleId);
    }

}
