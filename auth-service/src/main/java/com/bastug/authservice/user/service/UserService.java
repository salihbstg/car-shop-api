package com.bastug.authservice.user.service;

import com.bastug.authservice.auth.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

public interface UserService {
    String register(@Valid RegisterRequest registerRequest);
}
