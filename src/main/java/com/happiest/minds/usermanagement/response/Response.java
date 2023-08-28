
package com.happiest.minds.usermanagement.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class Response {

    private HttpStatus httpStatus;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
}
