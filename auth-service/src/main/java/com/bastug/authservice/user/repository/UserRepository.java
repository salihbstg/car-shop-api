package com.bastug.authservice.user.repository;

import com.bastug.authservice.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<User> findByEmailOrUsername(String email, String username);

    boolean existsByUsername(String username);
}
