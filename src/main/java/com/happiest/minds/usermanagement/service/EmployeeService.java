package com.happiest.minds.usermanagement.service;

import com.happiest.minds.usermanagement.entity.Employee;
import com.happiest.minds.usermanagement.exception.NotFoundException;
import com.happiest.minds.usermanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.happiest.minds.usermanagement.enums.Constants.*;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> createEmployees(List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    public Employee getEmployeeById(String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NO_DATA_FOUND.getValue() + id));
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployeeById(String id) {
        employeeRepository.deleteById(id);
    }

}
