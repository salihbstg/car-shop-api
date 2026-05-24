package com.bastug.carservice.exception;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(Long id) {
        super("ID ile araç bulunamadı -> " + id);
    }
}
