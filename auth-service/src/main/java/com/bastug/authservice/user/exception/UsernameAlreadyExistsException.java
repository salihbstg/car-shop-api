package com.bastug.authservice.user.exception;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(){
        super("Kullanıcı adı zaten kayıtlı!");
    }
}
