package com.bastug.customerservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateCustomerRequest(
        @NotNull(message = "İsim boş olamaz") @NotEmpty(message = "İsim boş olamaz")
        String firstName,
        @NotNull(message = "Soyad boş olamaz") @NotEmpty(message = "Soyad boş olamaz")
        String lastName,
        @NotNull(message = "Mail adresi boş olamaz") @NotEmpty(message = "Mail adresi boş olamaz")
        @Email(message = "Mail adresini kontrol edin!")
        String email,
        @NotNull(message = "Kullanıcı adı boş olamaz.")
        String username,
        @NotNull(message = "Telefon numarası boş olamaz") @NotEmpty(message = "Telefon numarası boş olamaz")
        @Pattern(
                regexp = "^(\\+90|0)?5[0-9]{9}$",
                message = "Telefon numarasını kontrol edin!"
        )
        String phone
) {
}
