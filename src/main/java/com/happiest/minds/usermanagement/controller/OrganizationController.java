package com.happiest.minds.usermanagement.controller;

import com.happiest.minds.usermanagement.entity.Organization;
import com.happiest.minds.usermanagement.request.OrganizationDTO;
import com.happiest.minds.usermanagement.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.happiest.minds.usermanagement.enums.Constants.*;

@RestController
@RequestMapping("api/user/management/organization")
@Slf4j
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<Organization> createOrganization(@Valid @RequestBody OrganizationDTO organizationDTO) {
        log.info("organizationDTO: {}", organizationDTO);
        Organization organization = new Organization();
        BeanUtils.copyProperties(organizationDTO, organization);
        organization = organizationService.createOrganization(organization);
        log.info("organization: {}", organization);
        return new ResponseEntity<>(organization, HttpStatus.CREATED);
    }

    @GetMapping("{orgId}")
    public ResponseEntity<Organization> getOrganizationById(@Valid @NonNull @PathVariable Integer orgId) {
        log.info("orgId: {}", orgId);
        Organization organization = organizationService.getOrganizationById(orgId);
        log.info("organization: {}", organization);
        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Organization>> getOrganizations() {
        List<Organization> organizations = organizationService.getOrganizations();
        log.info("organizations: {}", organizations);
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    @PutMapping("{orgId}")
    public ResponseEntity<Organization> updateOrganizationById(@Valid @NonNull @PathVariable Integer orgId,
                                                               @Valid @RequestBody OrganizationDTO organizationDTO) {
        log.info("orgId: {}, organizationDTO: {}", orgId, organizationDTO);
        if (organizationService.getOrganizationById(orgId) != null) {
            Organization organization = new Organization();
            BeanUtils.copyProperties(organizationDTO, organization);
            organization.setOrgId(orgId);
            organization = organizationService.createOrganization(organization);
            log.info("organization: {}", organization);
            return new ResponseEntity<>(organization, HttpStatus.OK);
        }
        log.error(NO_DATA_FOUND.getValue().concat(orgId.toString()));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{orgId}")
    public ResponseEntity<HttpStatus> deleteOrganizationById(@Valid @NonNull @PathVariable Integer orgId) {
        log.info("orgId: {}", orgId);
        if (organizationService.getOrganizationById(orgId) != null) {
            organizationService.deleteOrganizationById(orgId);
            log.info(RECORD_DELETED_FOR_ID.getValue().concat(orgId.toString()));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.error(NO_DATA_FOUND.getValue().concat(orgId.toString()));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
