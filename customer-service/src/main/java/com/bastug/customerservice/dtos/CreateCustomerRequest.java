package com.bastug.customerservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateCustomerRequest(
        @NotNull @NotEmpty
        String firstName,
        @NotNull @NotEmpty
        String lastName,
        @NotNull @NotEmpty
        @Email(message = "Mail adresini kontrol edin!")
        String email,
        @NotNull @NotEmpty
        @Pattern(
                regexp = "^(\\+90|0)?5[0-9]{9}$",
                message = "Telefon numarasını kontrol edin!"
        )
        String phone
) {
}
