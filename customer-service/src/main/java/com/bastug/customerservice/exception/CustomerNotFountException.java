package com.bastug.customerservice.exception;

public class CustomerNotFountException extends RuntimeException {
    public CustomerNotFountException(String identifier) {
        super("Verilen bilgi ile müşteri bulunamadı! -> "+identifier);
    }
}
