package com.happiest.minds.usermanagement.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.happiest.minds.usermanagement.entity.Organization;
import com.happiest.minds.usermanagement.exception.NotFoundException;
import com.happiest.minds.usermanagement.repository.OrganizationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class OrganizationServiceTest {

    @Mock
    private OrganizationRepository organizationRepository;

    @InjectMocks
    private OrganizationService organizationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrganization() {
        Organization organization = new Organization();

        when(organizationRepository.save(organization)).thenReturn(organization);

        Organization result = organizationService.createOrganization(organization);

        assertEquals(organization, result);
        verify(organizationRepository).save(organization);
    }

    @Test
    void testGetOrganizationById_ExistingOrganization() {
        Integer orgId = 1;
        Organization organization = new Organization();

        when(organizationRepository.findById(orgId)).thenReturn(Optional.of(organization));

        Organization result = organizationService.getOrganizationById(orgId);

        assertEquals(organization, result);
        verify(organizationRepository).findById(orgId);
    }

    @Test
    void testGetOrganizationById_NonExistingOrganization() {
        Integer orgId = 1;

        when(organizationRepository.findById(orgId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            organizationService.getOrganizationById(orgId);
        });
        verify(organizationRepository).findById(orgId);
    }

    @Test
    void testGetOrganizations() {
        List<Organization> organizations = new ArrayList<>();

        when(organizationRepository.findAll()).thenReturn(organizations);

        List<Organization> result = organizationService.getOrganizations();

        assertEquals(organizations, result);
        verify(organizationRepository).findAll();
    }

    @Test
    void testDeleteOrganizationById() {
        Integer orgId = 1;

        organizationService.deleteOrganizationById(orgId);

        verify(organizationRepository).deleteById(orgId);
    }

}