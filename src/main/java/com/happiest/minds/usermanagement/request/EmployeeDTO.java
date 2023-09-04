package com.happiest.minds.usermanagement.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class EmployeeDTO {

    @NotEmpty
    private String id;

    @NotEmpty
    private String name;

    @NonNull
    @Positive
    private Integer age;

    @NotEmpty
    private String email;

    @NotEmpty
    private String mobile;

    @NotEmpty
    private String location;

    @NonNull
    @Positive
    private Integer orgId;

    @NotEmpty
    private String department;
}
