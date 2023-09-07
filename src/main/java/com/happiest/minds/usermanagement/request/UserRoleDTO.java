package com.happiest.minds.usermanagement.request;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class UserRoleDTO {

    @NonNull
    @Positive
    private Integer userId;

    @NonNull
    @Positive
    private Integer roleId;
}
