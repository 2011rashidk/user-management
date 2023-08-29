package com.happiest.minds.usermanagement.controller;

import com.happiest.minds.usermanagement.dto.EmployeeDTO;
import com.happiest.minds.usermanagement.entity.Employee;
import com.happiest.minds.usermanagement.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/user/management/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        log.info("employeeDTO: {}", employeeDTO);
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        employee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PostMapping("employees")
    public ResponseEntity<List<Employee>> createEmployees(@RequestBody List<EmployeeDTO> employeeDTOList) {
        log.info("employeeDTOList: {}", employeeDTOList);
        List<Employee> employees = new ArrayList<Employee>();
        for (EmployeeDTO employeeDTO : employeeDTOList) {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDTO, employee);
            employees.add(employee);
        }
        employees = employeeService.createEmployees(employees);
        return new ResponseEntity<>(employees, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employees = employeeService.getEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable String id,
                                                       @RequestBody EmployeeDTO employeeDTO) {
        log.info("id: {}, employeeDTO: {}", id, employeeDTO);
        if (employeeService.getEmployeeById(id) != null) {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDTO, employee);
            employee.setId(employeeDTO.getId());
            employee = employeeService.createEmployee(employee);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployeeById(@PathVariable String id) {
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
