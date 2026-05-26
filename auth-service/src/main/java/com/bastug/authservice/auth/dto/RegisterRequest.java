package com.bastug.authservice.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @Email(message = "Geçersiz mail formatı!")
        @NotBlank(message = "Email boş olamaz!")
        String email,

        @NotBlank(message = "Kullanıcı adı boş olamaz!")
        @Size(min = 3, max = 20,
                message = "Kullanıcı adı 3-20 karakter olmalıdır!")
        String username,

        @NotBlank(message = "Şifre boş olamaz!")
        @Size(min = 6,
                message = "Şifre en az 6 karakter olmalıdır!")
        String password
) {
}
