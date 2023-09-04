package com.happiest.minds.usermanagement.service;

import com.happiest.minds.usermanagement.entity.Organization;
import com.happiest.minds.usermanagement.exception.NotFoundException;
import com.happiest.minds.usermanagement.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.happiest.minds.usermanagement.enums.Constants.*;

@Service
public class OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    public Organization createOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    public Organization getOrganizationById(Integer orgId) {
        return organizationRepository.findById(orgId)
                .orElseThrow(() -> new NotFoundException(NO_DATA_FOUND.getValue() + orgId));
    }

    public List<Organization> getOrganizations() {
        return organizationRepository.findAll();
    }

    public void deleteOrganizationById(Integer orgId) {
        organizationRepository.deleteById(orgId);
    }

}
