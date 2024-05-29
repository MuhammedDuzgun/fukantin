package com.gundemgaming.fukantin.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
public class UserAuthDto {

    @NotNull(message = "Username cannot be null")
    @NotEmpty(message = "Username cannot be empty.")
    @Size(
            min = 4,
            max = 16,
            message = "User must contain at least 4 and maximum 16 characters."
    )
    private String username;

    @NotNull(message = "Password cannot be null")
    @NotEmpty(message = "Password cannot be empty")
    @Size(
            min = 4,
            max = 16,
            message = "Password must contain at least 4 and maximum 16 characters"
    )
    private String password;

}
