package com.bastug.authservice.user.service;

import com.bastug.authservice.auth.dto.LoginResponse;
import com.bastug.authservice.auth.dto.RegisterResponse;
import com.bastug.authservice.auth.dto.LoginRequest;
import com.bastug.authservice.auth.dto.RegisterRequest;


public interface UserService {
    RegisterResponse register(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
}
