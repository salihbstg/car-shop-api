package com.bastug.authservice.auth.dto;

import com.bastug.authservice.user.entity.Role;

public record AuthResponse(
        String accessToken,
        Long userId,
        String username,
        String email,
        Role role
) {
}
