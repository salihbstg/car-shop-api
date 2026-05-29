package com.bastug.customerservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record  CustomerUpdateRequest(
        @Size(min = 2 , max = 30, message = "Adınız 2-30 karakter olmalıdır.")
        String firstName,
        @Size(min=2, max=30, message = "Soyadınız 2-30 karakter olmalıdır.")
        String lastName,
        @Email(message = "Mail adresini kontrol edin!")
        String email,
        @Pattern(
                regexp = "^(\\+90|0)?5[0-9]{9}$",
                message = "Telefon numarasını kontrol edin!"
        )
        String phone
) {
}
