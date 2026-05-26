package com.bastug.authservice.user.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> EmailAlreadyExists(
            EmailAlreadyExistsException ex
    ){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
