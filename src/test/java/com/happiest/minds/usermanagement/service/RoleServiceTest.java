package com.happiest.minds.usermanagement.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.happiest.minds.usermanagement.entity.Role;
import com.happiest.minds.usermanagement.exception.NotFoundException;
import com.happiest.minds.usermanagement.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateRole() {
        Role role = new Role();

        when(roleRepository.save(role)).thenReturn(role);

        Role result = roleService.createRole(role);

        assertEquals(role, result);
        verify(roleRepository).save(role);
    }

    @Test
    void testGetRoleById_ExistingRole() {
        Integer roleId = 1;
        Role role = new Role();

        when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));

        Role result = roleService.getRoleById(roleId);

        assertEquals(role, result);
        verify(roleRepository).findById(roleId);
    }

    @Test
    void testGetRoleById_NonExistingRole() {
        Integer roleId = 1;

        when(roleRepository.findById(roleId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            roleService.getRoleById(roleId);
        });
        verify(roleRepository).findById(roleId);
    }

    @Test
    void testGetAllRoles() {
        List<Role> roles = new ArrayList<>();

        when(roleRepository.findAll()).thenReturn(roles);

        List<Role> result = roleService.getAllRoles();

        assertEquals(roles, result);
        verify(roleRepository).findAll();
    }

    @Test
    void testDeleteRoleById() {
        Integer roleId = 1;

        roleService.deleteRole(roleId);

        verify(roleRepository).deleteById(roleId);
    }

}