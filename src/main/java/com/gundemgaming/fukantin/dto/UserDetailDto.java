package com.gundemgaming.fukantin.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Data
public class UserDetailDto {

    private Long id;

    @Size(max = 50, message = "Department must contain maximum 50 characters")
    private String department;

    @Size(max = 30, message = "Instagram must contain maximum 30 characters")
    private String instagram;

    @Size(max = 250, message = "Biography must contain maximum 250 characters.")
    private String biography;

    private Long userId;

}
