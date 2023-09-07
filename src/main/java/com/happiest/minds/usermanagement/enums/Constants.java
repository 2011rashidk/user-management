package com.happiest.minds.usermanagement.enums;

public enum Constants {

    EXCEPTION("Exception: {}"),
    BAD_CREDENTIALS("Bad credentials"),
    JWT_TOKEN_EXPIRED("JWT token expired!"),
    NO_DATA_FOUND("No data found with id: "),
    RECORD_DELETED_FOR_ID("Record deleted for id: "),
    JWT_SIGNATURE_DOES_NOT_MATCH("JWT signature doesn't match"),
    ACCESS_DENIED("Access denied!"),
    JWT_TOKEN_MALFORMED("JWT token malformed!"),
    USERNAME_NOT_FOUND("Username not found!"),
    ID("Id: {}"),
    EMPLOYEE_DTO("employeeDTO: {}"),
    EMPLOYEE("employee:{}"),
    LOGIN_DTO("loginDTO: {}"),
    REFRESH_TOKEN_CALLED("refreshToken() called"),
    ORGANIZATION_DTO("organizationDTO: {}"),
    ORGANIZATION("organization: {}"),
    PERMISSION_DTO("permissionDTO: {}"),
    PERMISSION("permission: {}"),
    USER_DTO("userDTO: {}"),
    ROLE_INPUT("Input:- role: {}"),
    ROLE("role: {}"),
    ROLE_PERMISSION_DTO("rolePermissionDTO: {}"),
    ROLE_PERMISSION("rolePermission: {}"),
    USER_INPUT("Input:- user: {}"),
    USER("user: {}"),
    USER_ROLE_DTO("userRoleDTO: {}"),
    USER_ROLE("userRole: {}");

    private final String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
