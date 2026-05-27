package com.bastug.authservice.auth.dto;

import com.bastug.authservice.user.dto.CustomerDetail;
import com.bastug.authservice.user.entity.Role;

public record RegisterResponse(
        String accessToken,
        Long userId,
        String email,
        String username,
        Role role,
        CustomerDetail customerDetail
) {
}
