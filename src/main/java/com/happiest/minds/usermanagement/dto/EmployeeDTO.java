package com.happiest.minds.usermanagement.dto;

import lombok.Data;

@Data
public class EmployeeDTO {

    private String id;
    private String name;
    private Integer age;
    private String email;
    private String mobile;
    private String location;
    private Integer orgId;
    private String department;
}
