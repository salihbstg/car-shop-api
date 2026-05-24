package com.bastug.customerservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CustomerUpdateRequest(
        @NotEmpty
        String firstName,
        @NotEmpty
        String lastName,
        @Email(message = "Mail adresini kontrol edin!")
        String email,
        @NotEmpty
        @Pattern(
                regexp = "^(\\+90|0)?5[0-9]{9}$",
                message = "Telefon numarasını kontrol edin!"
        )
        String phone
) {
}
