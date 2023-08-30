package com.happiest.minds.usermanagement.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class OrganizationDTO {

    @NotEmpty
    private String name;

    @NotEmpty
    private String location;
}
