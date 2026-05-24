package com.bastug.customerservice.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ExceptionResponse (
        Boolean success,
        LocalDateTime timestamp,
        Map<String,String> errors
){
}
