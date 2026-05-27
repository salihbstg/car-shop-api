package com.bastug.authservice.user.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("Kullanıcı bulunamadı!");
    }
}
