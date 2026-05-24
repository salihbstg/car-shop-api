package com.bastug.carservice.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ExceptionResponse (
        Boolean success,
        LocalDateTime timestamp,
        Map<String,String> errors
){
};
