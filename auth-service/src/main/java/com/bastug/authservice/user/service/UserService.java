package com.bastug.authservice.user.service;

import com.bastug.authservice.auth.dto.AuthResponse;
import com.bastug.authservice.auth.dto.LoginRequest;
import com.bastug.authservice.auth.dto.RegisterRequest;


public interface UserService {
    AuthResponse register(RegisterRequest registerRequest);
    AuthResponse login(LoginRequest loginRequest);
}
