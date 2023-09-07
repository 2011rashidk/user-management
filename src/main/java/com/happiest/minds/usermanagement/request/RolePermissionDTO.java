package com.happiest.minds.usermanagement.request;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class RolePermissionDTO {

    @NonNull
    @Positive
    private Integer roleId;

    @NonNull
    @Positive
    private Integer permissionId;
}
