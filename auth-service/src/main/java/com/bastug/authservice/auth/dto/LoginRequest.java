package com.bastug.authservice.auth.dto;

public record LoginRequest(
        String identifier,
        String password
){
}
