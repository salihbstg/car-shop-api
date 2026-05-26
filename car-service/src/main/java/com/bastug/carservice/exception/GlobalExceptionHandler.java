package com.bastug.carservice.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        ExceptionResponse exceptionResponse=new ExceptionResponse(
                false,
                LocalDateTime.now(),
                errors
        );
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map<String, String> errors = new HashMap<>();
        Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException invalidFormatException) {
            String fieldName=invalidFormatException.getPath().getFirst().getFieldName();
            if(fieldName.equals("fuelType"))
                errors.put("fuelType","Yakıt tipini kontrol edin!");
            if(fieldName.equals("transmissionType"))
                errors.put("transmissionType","Vites tipini kontrol edin!");
            if(fieldName.equals("carColor"))
                errors.put("carColor","Araç rengini kontrol edin!");
        }
        ExceptionResponse exceptionResponse=new ExceptionResponse(
                false,
                LocalDateTime.now(),
                errors
        );
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<String> handleCarNotFoundException(
            CarNotFoundException ex,
            HttpServletRequest request) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DuplicatePlateException.class)
    public ResponseEntity<String> handleDuplicatePlateException(
            DuplicatePlateException ex,
            HttpServletRequest request
    ){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }



    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignException(
            FeignException ex,
            HttpServletRequest request
    ){
        return ResponseEntity.status(ex.status()).body(ex.contentUTF8());
    }

}
