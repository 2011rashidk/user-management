package com.happiest.minds.usermanagement.controller;

import com.happiest.minds.usermanagement.request.EmployeeDTO;
import com.happiest.minds.usermanagement.entity.Employee;
import com.happiest.minds.usermanagement.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.happiest.minds.usermanagement.enums.Constants.*;

@RestController
@RequestMapping("api/user/management/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        log.info("employeeDTO: {}", employeeDTO);
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        employee = employeeService.createEmployee(employee);
        log.info("employee:{}", employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PostMapping("employees")
    public ResponseEntity<List<Employee>> createEmployees(@Valid @RequestBody List<EmployeeDTO> employeeDTOList) {
        log.info("employeeDTOList: {}", employeeDTOList);
        List<Employee> employees = new ArrayList<>();
        for (EmployeeDTO employeeDTO : employeeDTOList) {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDTO, employee);
            employees.add(employee);
        }
        employees = employeeService.createEmployees(employees);
        log.info("employees:{}", employees);
        return new ResponseEntity<>(employees, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@Valid @NotEmpty @PathVariable String id) {
        log.info("id: {}", id);
        Employee employee = employeeService.getEmployeeById(id);
        log.info("employee: {}", employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employees = employeeService.getEmployees();
        log.info("employees: {}", employees);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployeeById(@Valid @NotEmpty @PathVariable String id,
                                                       @Valid @RequestBody EmployeeDTO employeeDTO) {
        log.info("id: {}, employeeDTO: {}", id, employeeDTO);
        if (employeeService.getEmployeeById(id) != null) {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDTO, employee);
            employee.setId(employeeDTO.getId());
            employee = employeeService.createEmployee(employee);
            log.info("employee: {}", employee);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        log.error(NO_DATA_FOUND.getValue().concat(id));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployeeById(@Valid @NotEmpty @PathVariable String id) {
        log.info("id: {}", id);
        if (employeeService.getEmployeeById(id) != null) {
            employeeService.deleteEmployeeById(id);
            log.info(RECORD_DELETED_FOR_ID.getValue().concat(id));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.error(NO_DATA_FOUND.getValue().concat(id));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
