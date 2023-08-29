package com.happiest.minds.usermanagement.repository;

import com.happiest.minds.usermanagement.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
}
