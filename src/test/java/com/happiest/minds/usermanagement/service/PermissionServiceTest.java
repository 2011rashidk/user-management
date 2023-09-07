package com.happiest.minds.usermanagement.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.happiest.minds.usermanagement.entity.Permission;
import com.happiest.minds.usermanagement.exception.NotFoundException;
import com.happiest.minds.usermanagement.repository.PermissionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PermissionServiceTest {

    @Mock
    private PermissionRepository permissionRepository;

    @InjectMocks
    private PermissionService permissionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePermission() {
        Permission permission = new Permission();

        when(permissionRepository.save(permission)).thenReturn(permission);

        Permission result = permissionService.createPermission(permission);

        assertEquals(permission, result);
        verify(permissionRepository).save(permission);
    }

    @Test
    void testGetPermissionById_ExistingPermission() {
        Integer permissionId = 1;
        Permission permission = new Permission();

        when(permissionRepository.findById(permissionId)).thenReturn(Optional.of(permission));

        Permission result = permissionService.getPermissionById(permissionId);

        assertEquals(permission, result);
        verify(permissionRepository).findById(permissionId);
    }

    @Test
    void testGetPermissionById_NonExistingPermission() {
        Integer permissionId = 1;

        when(permissionRepository.findById(permissionId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            permissionService.getPermissionById(permissionId);
        });
        verify(permissionRepository).findById(permissionId);
    }

    @Test
    void testGetAllPermissions() {
        List<Permission> permissions = new ArrayList<>();

        when(permissionRepository.findAll()).thenReturn(permissions);

        List<Permission> result = permissionService.getAllPermissions();

        assertEquals(permissions, result);
        verify(permissionRepository).findAll();
    }

    @Test
    void testDeletePermissionById() {
        Integer permissionId = 1;

        permissionService.deletePermission(permissionId);

        verify(permissionRepository).deleteById(permissionId);
    }

}