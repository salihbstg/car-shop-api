package com.bastug.authservice.user.exception;

public class PhoneAlreadyExistsException extends RuntimeException {
    public PhoneAlreadyExistsException(){
        super("Telefon numarası zaten kayıtlı!");
    }
}
