package com.happiest.minds.usermanagement.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDTO {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
