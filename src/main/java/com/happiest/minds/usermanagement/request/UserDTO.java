package com.happiest.minds.usermanagement.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDTO {

    private Integer userId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    @NotEmpty
    private String mobile;

    @NotEmpty
    private String username;

    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
