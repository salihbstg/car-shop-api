package com.bastug.carservice.exception;


public class DuplicatePlateException extends RuntimeException {
    public DuplicatePlateException(String plate) {
        super("Plaka daha önce kayıtlı! -> "+plate);
    }
}
