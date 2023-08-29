package com.happiest.minds.usermanagement.repository;

import com.happiest.minds.usermanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
