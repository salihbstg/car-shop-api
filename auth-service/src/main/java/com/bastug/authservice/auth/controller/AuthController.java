package com.bastug.authservice.auth.controller;

import com.bastug.authservice.auth.dto.LoginResponse;
import com.bastug.authservice.auth.dto.RegisterResponse;
import com.bastug.authservice.auth.dto.LoginRequest;
import com.bastug.authservice.auth.dto.RegisterRequest;
import com.bastug.authservice.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest registerRequest) {
        return ResponseEntity.ok(userService.register(registerRequest));
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest){
        return ResponseEntity.ok(userService.login(loginRequest));
    }
}
