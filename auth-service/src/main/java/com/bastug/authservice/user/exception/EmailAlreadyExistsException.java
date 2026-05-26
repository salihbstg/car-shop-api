package com.bastug.authservice.user.exception;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String email){
        super("Mail adresi zaten kayıtlı -> "+email);
    }
}
