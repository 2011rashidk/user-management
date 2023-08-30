package com.happiest.minds.usermanagement.request;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserRoleDTO {

    @NonNull
    @Positive
    private Integer userId;

    @NonNull
    @Positive
    private Integer roleId;
}
