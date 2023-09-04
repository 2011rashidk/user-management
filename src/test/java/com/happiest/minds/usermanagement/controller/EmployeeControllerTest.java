package com.happiest.minds.usermanagement.controller;

import com.happiest.minds.usermanagement.entity.Employee;
import com.happiest.minds.usermanagement.request.EmployeeDTO;
import com.happiest.minds.usermanagement.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO();

        Employee employee = new Employee();

        when(employeeService.createEmployee(any(Employee.class))).thenReturn(employee);

        ResponseEntity<Employee> response = employeeController.createEmployee(employeeDTO);

        verify(employeeService).createEmployee(any(Employee.class)); // Verify service method was called
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(employee, response.getBody());
    }

    @Test
    void testCreateEmployees() {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();

        List<Employee> employees = new ArrayList<>();

        when(employeeService.createEmployee(any(Employee.class))).thenAnswer(invocation -> {
            Employee createdEmployee = invocation.getArgument(0);
            employees.add(createdEmployee);
            return createdEmployee;
        });

        ResponseEntity<List<Employee>> response = employeeController.createEmployees(employeeDTOList);

        verify(employeeService, times(employeeDTOList.size())).createEmployee(any(Employee.class)); // Verify service method was called for each employeeDTO
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(employees, response.getBody());

        verify(employeeService).createEmployees(employees);
        assertEquals(employees, response.getBody());

        for (int i = 0; i < employeeDTOList.size(); i++) {
            Employee employee = employees.get(i);
            verify(employeeService).createEmployee(employee);
        }
    }

    @Test
    void testGetEmployeeById() {
        String employeeId = "123";
        Employee employee = new Employee();

        when(employeeService.getEmployeeById(employeeId)).thenReturn(employee);

        ResponseEntity<Employee> response = employeeController.getEmployeeById(employeeId);

        verify(employeeService).getEmployeeById(employeeId); // Verify service method was called with correct parameter
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee, response.getBody());
    }

    @Test
    void testGetEmployees() {
        List<Employee> employees = new ArrayList<>();

        when(employeeService.getEmployees()).thenReturn(employees);

        ResponseEntity<List<Employee>> response = employeeController.getEmployees();

        verify(employeeService).getEmployees();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employees, response.getBody());
    }

//    @Test
//    void testUpdateEmployeeByIdPositive() {
//        String employeeId = "123";
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//
//        when(employeeService.getEmployeeById(employeeId)).thenReturn(null);
//
//        ResponseEntity<Employee> response = employeeController.updateEmployeeById(employeeDTO);
//
//        verify(employeeService).getEmployeeById(employeeId);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertNull(response.getBody());
//    }

    @Test
    void testDeleteEmployeeByIdPositive() {
        String employeeId = "123";
        Employee existingEmployee = new Employee();

        when(employeeService.getEmployeeById(employeeId)).thenReturn(existingEmployee);

        ResponseEntity<HttpStatus> response = employeeController.deleteEmployeeById(employeeId);

        verify(employeeService).getEmployeeById(employeeId);
        verify(employeeService).deleteEmployeeById(employeeId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

}
