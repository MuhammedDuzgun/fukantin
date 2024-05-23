package com.gundemgaming.fukantin.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Data
public class UserDetailDto {
    private Long id;

    @Size(
            max = 50,
            message = "Fakulte bilgisi en fazla 75 karakter icermelidir."
    )
    private String department;

    @Size(
            max = 30,
            message = "Instagram adresi en fazla 30 karakter icermelidir."
    )
    private String instagram;

    @Size(
            max = 250,
            message = "Biyografi en fazla 250 karakter icermelidir."
    )
    private String biography;
}
