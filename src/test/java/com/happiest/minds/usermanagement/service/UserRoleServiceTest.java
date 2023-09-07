package com.happiest.minds.usermanagement.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.happiest.minds.usermanagement.entity.UserRole;
import com.happiest.minds.usermanagement.repository.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserRoleServiceTest {

    @Mock
    private UserRoleRepository userRoleRepository;

    @InjectMocks
    private UserRoleService userRoleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddRoleToUser() {
        UserRole userRole = new UserRole();

        when(userRoleRepository.save(userRole)).thenReturn(userRole);

        UserRole result = userRoleService.addRoleToUser(userRole);

        assertEquals(userRole, result);
        verify(userRoleRepository).save(userRole);
    }

    @Test
    void testGetByUserIdAndRoleId_ExistingUserRole() {
        UserRole userRole = new UserRole();

        when(userRoleRepository.findByUserIdAndRoleId(userRole.getUserId(), userRole.getRoleId())).thenReturn(userRole);

        UserRole result = userRoleService.getByUserIdAndRoleId(userRole);

        assertEquals(userRole, result);
        verify(userRoleRepository).findByUserIdAndRoleId(userRole.getUserId(), userRole.getRoleId());
    }

    @Test
    void testGetByUserIdAndRoleId_NonExistingUserRole() {
        UserRole userRole = new UserRole();

        when(userRoleRepository.findByUserIdAndRoleId(userRole.getUserId(), userRole.getRoleId())).thenReturn(null);

        UserRole result = userRoleService.getByUserIdAndRoleId(userRole);

        assertNull(result);
        verify(userRoleRepository).findByUserIdAndRoleId(userRole.getUserId(), userRole.getRoleId());
    }

    @Test
    void testDeleteByUserIdAndRoleId() {
        UserRole userRole = new UserRole();

        userRoleService.deleteByUserIdAndRoleId(userRole);

        verify(userRoleRepository).deleteByUserIdAndRoleId(userRole.getUserId(), userRole.getRoleId());
    }

}