package com.happiest.minds.usermanagement.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RoleDTO {

    private String roleName;
    private Set<PermissionDTO> permissions = new HashSet<>();
}
