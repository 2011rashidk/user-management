package com.happiest.minds.usermanagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import com.happiest.minds.usermanagement.entity.Permission;
import com.happiest.minds.usermanagement.request.PermissionDTO;
import com.happiest.minds.usermanagement.service.PermissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

class PermissionControllerTest {

    @Mock
    private PermissionService permissionService;

    @InjectMocks
    private PermissionController permissionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePermission() {
        PermissionDTO permissionDTO = new PermissionDTO();

        Permission permission = new Permission();

        when(permissionService.createPermission(any(Permission.class))).thenReturn(permission);

        ResponseEntity<Permission> response = permissionController.createPermission(permissionDTO);

        verify(permissionService).createPermission(any(Permission.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(permission, response.getBody());
    }
    @Test
    void testGetPermissionById() {
        Integer permissionId = 123;
        Permission permission = new Permission();

        when(permissionService.getPermissionById(permissionId)).thenReturn(permission);

        ResponseEntity<Permission> response = permissionController.getPermissionById(permissionId);

        verify(permissionService).getPermissionById(permissionId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(permission, response.getBody());
    }

    @Test
    void testGetAllPermissions() {
        List<Permission> permissions = new ArrayList<>();

        when(permissionService.getAllPermissions()).thenReturn(permissions);

        ResponseEntity<List<Permission>> response = permissionController.getAllPermissions();

        verify(permissionService).getAllPermissions();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(permissions, response.getBody());
    }
    @Test
    void testUpdatePermissionById_ExistingPermission() {
        Integer permissionId = 123;
        PermissionDTO permissionDTO = new PermissionDTO();

        Permission permission = new Permission();

        when(permissionService.getPermissionById(permissionId)).thenReturn(new Permission());
        when(permissionService.createPermission(any(Permission.class))).thenReturn(permission);

        ResponseEntity<Permission> response = permissionController.updatePermissionById(permissionId, permissionDTO);

        verify(permissionService).getPermissionById(permissionId);
        verify(permissionService).createPermission(any(Permission.class));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(permission, response.getBody());
    }

    @Test
    void testUpdatePermissionById_NonExistingPermission() {
        Integer permissionId = 123;
        PermissionDTO permissionDTO = new PermissionDTO();

        when(permissionService.getPermissionById(permissionId)).thenReturn(null);

        ResponseEntity<Permission> response = permissionController.updatePermissionById(permissionId, permissionDTO);

        verify(permissionService).getPermissionById(permissionId);
        verify(permissionService, never()).createPermission(any(Permission.class));
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }


    @Test
    void testDeletePermission_ExistingPermission() {
        Integer permissionId = 123;

        when(permissionService.getPermissionById(permissionId)).thenReturn(new Permission());

        ResponseEntity<HttpStatus> response = permissionController.deletePermission(permissionId);

        verify(permissionService).getPermissionById(permissionId);
        verify(permissionService).deletePermission(permissionId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeletePermission_NonExistingPermission() {
        Integer permissionId = 123;

        when(permissionService.getPermissionById(permissionId)).thenReturn(null);

        ResponseEntity<HttpStatus> response = permissionController.deletePermission(permissionId);

        verify(permissionService).getPermissionById(permissionId);
        verify(permissionService, never()).deletePermission(anyInt());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

}