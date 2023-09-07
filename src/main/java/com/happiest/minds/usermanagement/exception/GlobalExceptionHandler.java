package com.happiest.minds.usermanagement.exception;

import com.happiest.minds.usermanagement.response.Response;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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
        log.error(EXCEPTION.getValue(), ex.getMessage());
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
    public ResponseEntity<Response> tokenExpiredException(ExpiredJwtException e) {
        log.error(EXCEPTION.getValue(), e.getMessage());
        Response response = new Response(HttpStatus.BAD_REQUEST, JWT_TOKEN_EXPIRED.getValue(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> resourceNotFoundException(NotFoundException ex) {
        log.error(EXCEPTION.getValue(), ex.getMessage());
        Response response = new Response(HttpStatus.NOT_FOUND, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<Response> signatureException(SignatureException ex) {
        log.error(EXCEPTION.getValue(), ex.getMessage());
        Response response = new Response(HttpStatus.BAD_REQUEST, JWT_SIGNATURE_DOES_NOT_MATCH.getValue(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Response> accessDeniedException(AccessDeniedException ex) {
        log.error(EXCEPTION.getValue(), ex.getMessage());
        Response response = new Response(HttpStatus.FORBIDDEN, ACCESS_DENIED.getValue(), null);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<Response> malformedException(MalformedJwtException ex) {
        log.error(EXCEPTION.getValue(), ex.getMessage());
        Response response = new Response(HttpStatus.BAD_REQUEST, JWT_TOKEN_MALFORMED.getValue(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
