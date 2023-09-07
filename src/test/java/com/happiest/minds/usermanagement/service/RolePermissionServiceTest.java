package com.happiest.minds.usermanagement.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.happiest.minds.usermanagement.entity.RolePermission;
import com.happiest.minds.usermanagement.repository.RolePermissionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RolePermissionServiceTest {

    @Mock
    private RolePermissionRepository rolePermissionRepository;

    @InjectMocks
    private RolePermissionService rolePermissionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPermissionToRole() {
        RolePermission rolePermission = new RolePermission();

        when(rolePermissionRepository.save(rolePermission)).thenReturn(rolePermission);

        RolePermission result = rolePermissionService.addPermissionToRole(rolePermission);

        assertEquals(rolePermission, result);
        verify(rolePermissionRepository).save(rolePermission);
    }

    @Test
    void testGetByRoleIdAndPermissionId_ExistingRolePermission() {
        RolePermission rolePermission = new RolePermission();

        when(rolePermissionRepository.getByRoleIdAndPermissionId(rolePermission.getRoleId(), rolePermission.getPermissionId())).thenReturn(rolePermission);

        RolePermission result = rolePermissionService.getByRoleIdAndPermissionId(rolePermission);

        assertEquals(rolePermission, result);
        verify(rolePermissionRepository).getByRoleIdAndPermissionId(rolePermission.getRoleId(), rolePermission.getPermissionId());
    }

    @Test
    void testGetByRoleIdAndPermissionId_NonExistingRolePermission() {
        RolePermission rolePermission = new RolePermission();

        when(rolePermissionRepository.getByRoleIdAndPermissionId(rolePermission.getRoleId(), rolePermission.getPermissionId())).thenReturn(null);

        RolePermission result = rolePermissionService.getByRoleIdAndPermissionId(rolePermission);

        assertNull(result);
        verify(rolePermissionRepository).getByRoleIdAndPermissionId(rolePermission.getRoleId(), rolePermission.getPermissionId());
    }

    @Test
    void testDeleteByRoleIdAndPermissionId() {
        RolePermission rolePermission = new RolePermission();

        rolePermissionService.deleteByRoleIdAndPermissionId(rolePermission);

        verify(rolePermissionRepository).deleteByRoleIdAndPermissionId(rolePermission.getRoleId(), rolePermission.getPermissionId());
    }

}