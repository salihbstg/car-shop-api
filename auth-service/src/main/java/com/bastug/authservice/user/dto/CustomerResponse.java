package com.bastug.authservice.user.dto;


import java.time.LocalDate;

public record CustomerResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        LocalDate createdAt
) {
}