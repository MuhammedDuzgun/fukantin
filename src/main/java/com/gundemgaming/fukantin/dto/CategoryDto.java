package com.gundemgaming.fukantin.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDto {

    private Long id;

    @NotNull(message = "Category cannot be null")
    @NotEmpty(message = "Category cannot be empty")
    @Size(max = 30, message = "Category must contain maximum 30 characters.")
    private String category;

}
