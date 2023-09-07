package com.happiest.minds.usermanagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import com.happiest.minds.usermanagement.entity.Role;
import com.happiest.minds.usermanagement.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;


class RoleControllerTest {

    @Mock
    private RoleService roleService;

    @InjectMocks
    private RoleController roleController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateRole() {
        Role role = new Role();

        when(roleService.createRole(any(Role.class))).thenReturn(role);

        ResponseEntity<Role> response = roleController.createRole(role);

        verify(roleService).createRole(any(Role.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(role, response.getBody());
    }

    @Test
    void testGetRoleById() {
        Integer roleId = 123;
        Role role = new Role();

        when(roleService.getRoleById(roleId)).thenReturn(role);

        ResponseEntity<Role> response = roleController.getRoleById(roleId);

        verify(roleService).getRoleById(roleId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(role, response.getBody());
    }

    @Test
    void testGetAllRoles() {
        List<Role> roles = new ArrayList<>();

        when(roleService.getAllRoles()).thenReturn(roles);

        ResponseEntity<List<Role>> response = roleController.getAllRoles();

        verify(roleService).getAllRoles();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(roles, response.getBody());
    }

    @Test
    void testUpdateRoleById_ExistingRole() {
        Integer roleId = 123;
        Role role = new Role();

        when(roleService.getRoleById(roleId)).thenReturn(new Role());
        when(roleService.createRole(any(Role.class))).thenReturn(role);

        ResponseEntity<Role> response = roleController.updateRoleById(roleId, role);

        verify(roleService).getRoleById(roleId);
        verify(roleService).createRole(any(Role.class));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(role, response.getBody());
    }

    @Test
    void testUpdateRoleById_NonExistingRole() {
        Integer roleId = 123;
        Role role = new Role();

        when(roleService.getRoleById(roleId)).thenReturn(null);

        ResponseEntity<Role> response = roleController.updateRoleById(roleId, role);

        verify(roleService).getRoleById(roleId);
        verify(roleService, never()).createRole(any(Role.class));
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteRole_ExistingRole() {
        Integer roleId = 123;

        when(roleService.getRoleById(roleId)).thenReturn(new Role());

        ResponseEntity<HttpStatus> response = roleController.deleteRole(roleId);

        verify(roleService).getRoleById(roleId);
        verify(roleService).deleteRole(roleId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteRole_NonExistingRole() {
        Integer roleId = 123;

        when(roleService.getRoleById(roleId)).thenReturn(null);

        ResponseEntity<HttpStatus> response = roleController.deleteRole(roleId);

        verify(roleService).getRoleById(roleId);
        verify(roleService, never()).deleteRole(anyInt());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

}