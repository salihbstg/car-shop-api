package com.bastug.customerservice.dtos;

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
