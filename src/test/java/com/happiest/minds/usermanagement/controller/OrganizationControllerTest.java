package com.happiest.minds.usermanagement.controller;

import com.happiest.minds.usermanagement.entity.Organization;
import com.happiest.minds.usermanagement.request.OrganizationDTO;
import com.happiest.minds.usermanagement.service.OrganizationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrganizationControllerTest {
    @Mock
    private OrganizationService organizationService;

    @InjectMocks
    private OrganizationController organizationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrganization() {
        OrganizationDTO organizationDTO = new OrganizationDTO();
        Organization organization = new Organization();
        when(organizationService.createOrganization(any(Organization.class))).thenReturn(organization);

        ResponseEntity<Organization> response = organizationController.createOrganization(organizationDTO);

        verify(organizationService).createOrganization(any(Organization.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(organization, response.getBody());
    }

    @Test
    void testGetOrganizationById() {
        Integer orgId = 123;
        Organization organization = new Organization();

        when(organizationService.getOrganizationById(orgId)).thenReturn(organization);

        ResponseEntity<Organization> response = organizationController.getOrganizationById(orgId);
        verify(organizationService).getOrganizationById(orgId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(organization, response.getBody());
    }

    @Test
    void testGetOrganizations() {
        List<Organization> organizations = new ArrayList<>();
        when(organizationService.getOrganizations()).thenReturn(organizations);

        ResponseEntity<List<Organization>> response = organizationController.getOrganizations();

        verify(organizationService).getOrganizations();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(organizations, response.getBody());
    }

    @Test
    void testUpdateOrganizationById_ExistingOrganization() {
        Integer orgId = 123;
        OrganizationDTO organizationDTO = new OrganizationDTO();
        Organization organization = new Organization();

        when(organizationService.getOrganizationById(orgId)).thenReturn(new Organization());
        when(organizationService.createOrganization(any(Organization.class))).thenReturn(organization);

        ResponseEntity<Organization> response = organizationController.updateOrganizationById(orgId, organizationDTO);

        verify(organizationService).getOrganizationById(orgId);
        verify(organizationService).createOrganization(any(Organization.class));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(organization, response.getBody());
    }

    @Test
    void testUpdateOrganizationById_NonExistingOrganization() {
        Integer orgId = 123;
        OrganizationDTO organizationDTO = new OrganizationDTO();

        when(organizationService.getOrganizationById(orgId)).thenReturn(null);

        ResponseEntity<Organization> response = organizationController.updateOrganizationById(orgId, organizationDTO);

        verify(organizationService).getOrganizationById(orgId);
        verify(organizationService, never()).createOrganization(any(Organization.class));
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteOrganizationById_ExistingOrganization() {
        Integer orgId = 123;

        when(organizationService.getOrganizationById(orgId)).thenReturn(new Organization());

        ResponseEntity<HttpStatus> response = organizationController.deleteOrganizationById(orgId);

        verify(organizationService).getOrganizationById(orgId);
        verify(organizationService).deleteOrganizationById(orgId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteOrganizationById_NonExistingOrganization() {
        Integer orgId = 123;

        when(organizationService.getOrganizationById(orgId)).thenReturn(null);

        ResponseEntity<HttpStatus> response = organizationController.deleteOrganizationById(orgId);

        verify(organizationService).getOrganizationById(orgId);
        verify(organizationService, never()).deleteOrganizationById(anyInt());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}