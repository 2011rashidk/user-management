package com.happiest.minds.usermanagement.controller;

import com.happiest.minds.usermanagement.dto.OrganizationDTO;
import com.happiest.minds.usermanagement.entity.Organization;
import com.happiest.minds.usermanagement.service.OrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user/management/organization")
@Slf4j
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<Organization> createOrganization(@RequestBody OrganizationDTO organizationDTO) {
        log.info("organizationDTO: {}", organizationDTO);
        Organization organization = new Organization();
        BeanUtils.copyProperties(organizationDTO, organization);
        organization = organizationService.createOrganization(organization);
        return new ResponseEntity<>(organization, HttpStatus.CREATED);
    }

    @GetMapping("{orgId}")
    public ResponseEntity<Organization> getOrganizationById(@PathVariable Integer orgId) {
        log.info("orgId: {}", orgId);
        Organization organization = organizationService.getOrganizationById(orgId);
        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Organization>> getOrganizations() {
        List<Organization> organizations = organizationService.getOrganizations();
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    @PutMapping("{orgId}")
    public ResponseEntity<Organization> updateOrganizationById(@PathVariable Integer orgId,
                                                               @RequestBody OrganizationDTO organizationDTO) {
        log.info("orgId: {}, organizationDTO: {}", orgId, organizationDTO);
        if (organizationService.getOrganizationById(orgId) != null) {
            Organization organization = new Organization();
            BeanUtils.copyProperties(organizationDTO, organization);
            organization.setOrgId(orgId);
            organization = organizationService.createOrganization(organization);
            return new ResponseEntity<>(organization, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{orgId}")
    public ResponseEntity<HttpStatus> deleteOrganizationById(@PathVariable Integer orgId) {
        organizationService.deleteOrganizationById(orgId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
