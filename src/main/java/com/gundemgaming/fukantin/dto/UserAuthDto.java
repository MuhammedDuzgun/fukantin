package com.gundemgaming.fukantin.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
public class UserAuthDto {

    @NotEmpty(
            message = "Kullanici ismi bos birakilamaz."
    )
    @NotNull
    @Size(
            min = 4,
            max = 16,
            message = "Kullanici adi en az 4 en fazla 16 karakter icermelidir."
    )
    private String username;

    @NotEmpty(
            message = "Sifre bos birakilamaz."
    )
    @Size(
            min = 4,
            max = 16,
            message = "Sifre en az 4 en fazla 16 karakter icermelidir."
    )
    private String password;

}
