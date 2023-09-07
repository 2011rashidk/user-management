package com.happiest.minds.usermanagement.controller;

import com.happiest.minds.usermanagement.entity.UserRole;
import com.happiest.minds.usermanagement.request.UserRoleDTO;
import com.happiest.minds.usermanagement.service.UserRoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class UserRoleControllerTest {
    @Mock
    private UserRoleService userRoleService;

    @InjectMocks
    private UserRoleController userRoleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddRoleToUser() {
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        UserRole userRole = new UserRole();
        when(userRoleService.addRoleToUser(any(UserRole.class))).thenReturn(userRole);
        ResponseEntity<UserRole> response = userRoleController.addRoleToUser(userRoleDTO);
        verify(userRoleService).addRoleToUser(any(UserRole.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userRole, response.getBody());
    }

    @Test
    void testDeleteRoleOfUser_ExistingRole() {
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        UserRole userRole = new UserRole();
        when(userRoleService.getByUserIdAndRoleId(any(UserRole.class))).thenReturn(userRole);
        ResponseEntity<HttpStatus> response = userRoleController.deleteRoleOfUser(userRoleDTO);
        verify(userRoleService).getByUserIdAndRoleId(any(UserRole.class));
        verify(userRoleService).deleteByUserIdAndRoleId(any(UserRole.class));
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteRoleOfUser_NonExistingRole() {
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        when(userRoleService.getByUserIdAndRoleId(any(UserRole.class))).thenReturn(null);
        ResponseEntity<HttpStatus> response = userRoleController.deleteRoleOfUser(userRoleDTO);
        verify(userRoleService).getByUserIdAndRoleId(any(UserRole.class));
        verify(userRoleService, never()).deleteByUserIdAndRoleId(any(UserRole.class));
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

}