package com.gundemgaming.fukantin.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {

    @NotEmpty(
            message = "Kullanici ismi bos birakilamaz."
    )
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
