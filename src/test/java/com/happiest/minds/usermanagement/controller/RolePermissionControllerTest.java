package com.happiest.minds.usermanagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import com.happiest.minds.usermanagement.entity.RolePermission;
import com.happiest.minds.usermanagement.request.RolePermissionDTO;
import com.happiest.minds.usermanagement.service.RolePermissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


class RolePermissionControllerTest {

    @Mock
    private RolePermissionService rolePermissionService;

    @InjectMocks
    private RolePermissionController rolePermissionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPermissionToRole() {
        RolePermissionDTO rolePermissionDTO = new RolePermissionDTO();

        RolePermission rolePermission = new RolePermission();

        when(rolePermissionService.addPermissionToRole(any(RolePermission.class))).thenReturn(rolePermission);

        ResponseEntity<RolePermission> response = rolePermissionController.addPermissionToRole(rolePermissionDTO);

        verify(rolePermissionService).addPermissionToRole(any(RolePermission.class));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rolePermission, response.getBody());
    }

    @Test
    void testDeletePermissionOfRole_ExistingRolePermission() {
        RolePermissionDTO rolePermissionDTO = new RolePermissionDTO();

        RolePermission rolePermission = new RolePermission();

        when(rolePermissionService.getByRoleIdAndPermissionId(any(RolePermission.class))).thenReturn(rolePermission);

        ResponseEntity<HttpStatus> response = rolePermissionController.deletePermissionOfRole(rolePermissionDTO);

        verify(rolePermissionService).getByRoleIdAndPermissionId(any(RolePermission.class));
        verify(rolePermissionService).deleteByRoleIdAndPermissionId(any(RolePermission.class));
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeletePermissionOfRole_NonExistingRolePermission() {
        RolePermissionDTO rolePermissionDTO = new RolePermissionDTO();

        when(rolePermissionService.getByRoleIdAndPermissionId(any(RolePermission.class))).thenReturn(null);

        ResponseEntity<HttpStatus> response = rolePermissionController.deletePermissionOfRole(rolePermissionDTO);

        verify(rolePermissionService).getByRoleIdAndPermissionId(any(RolePermission.class));
        verify(rolePermissionService, never()).deleteByRoleIdAndPermissionId(any(RolePermission.class));
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}