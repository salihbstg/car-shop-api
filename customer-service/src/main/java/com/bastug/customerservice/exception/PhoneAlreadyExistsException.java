package com.bastug.customerservice.exception;

public class PhoneAlreadyExistsException extends RuntimeException{
    public PhoneAlreadyExistsException(String phone){
        super("Telefon numarası zaten kayıtlı -> "+ phone);
    }
}
