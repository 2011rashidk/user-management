package com.happiest.minds.usermanagement.exception;

import com.happiest.minds.usermanagement.response.Response;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.happiest.minds.usermanagement.enums.Constants.*;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> globalExceptionHandler(Exception ex) {
        log.error("Exception: {}", ex.getMessage());
        ex.printStackTrace();
        Response response = new Response(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Response> badCredentialsExceptionHandler() {
        log.error(BAD_CREDENTIALS.getValue());
        Response response = new Response(HttpStatus.BAD_REQUEST, BAD_CREDENTIALS.getValue(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Response> tokenExpiredException() {
        log.error(BAD_CREDENTIALS.getValue());
        Response response = new Response(HttpStatus.BAD_REQUEST, JWT_TOKEN_EXPIRED.getValue(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response> resourceNotFoundException(ResourceNotFoundException ex) {
        log.error(ex.getMessage());
        Response response = new Response(HttpStatus.BAD_REQUEST, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
