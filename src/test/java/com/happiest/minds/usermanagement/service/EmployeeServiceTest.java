package com.happiest.minds.usermanagement.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.happiest.minds.usermanagement.entity.Employee;
import com.happiest.minds.usermanagement.exception.NotFoundException;
import com.happiest.minds.usermanagement.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEmployee() {
        Employee employee = new Employee();

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeService.createEmployee(employee);

        assertEquals(employee, result);
        verify(employeeRepository).save(employee);
    }

    @Test
    void testCreateEmployees() {
        List<Employee> employees = new ArrayList<>();

        when(employeeRepository.saveAll(employees)).thenReturn(employees);

        List<Employee> result = employeeService.createEmployees(employees);

        assertEquals(employees, result);
        verify(employeeRepository).saveAll(employees);
    }

    @Test
    void testGetEmployeeById_ExistingEmployee() {
        String id = "123";
        Employee employee = new Employee();

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployeeById(id);

        assertEquals(employee, result);
        verify(employeeRepository).findById(id);
    }

    @Test
    void testGetEmployeeById_NonExistingEmployee() {
        String id = "123";

        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            employeeService.getEmployeeById(id);
        });
        verify(employeeRepository).findById(id);
    }

    @Test
    void testGetEmployees() {
        List<Employee> employees = new ArrayList<>();

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getEmployees();

        assertEquals(employees, result);
        verify(employeeRepository).findAll();
    }

    @Test
    void testDeleteEmployeeById() {
        String id = "123";

        employeeService.deleteEmployeeById(id);

        verify(employeeRepository).deleteById(id);
    }
}
