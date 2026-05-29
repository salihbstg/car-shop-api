package com.bastug.authservice.user.dto;

public record CustomerDetail(
        String firstName,
        String lastName,
        String email,
        String username,
        String phone
) {
}
