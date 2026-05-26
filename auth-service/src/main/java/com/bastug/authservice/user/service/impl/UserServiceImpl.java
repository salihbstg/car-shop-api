package com.bastug.authservice.user.service.impl;

import com.bastug.authservice.auth.dto.RegisterRequest;
import com.bastug.authservice.security.JwtService;
import com.bastug.authservice.user.entity.Role;
import com.bastug.authservice.user.entity.User;
import com.bastug.authservice.user.exception.EmailAlreadyExistsException;
import com.bastug.authservice.user.repository.UserRepository;
import com.bastug.authservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @Override
    public String register(RegisterRequest registerRequest) {
        if(userRepository.existsByEmail(registerRequest.email())){
            throw new EmailAlreadyExistsException(registerRequest.email());
        }
        User user=new User();
        user.setRole(Role.USER);
        user.setEmail(registerRequest.email());
        user.setUsername(registerRequest.username());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        userRepository.save(user);
        return jwtService.generateToken(registerRequest.email());
    }
}
