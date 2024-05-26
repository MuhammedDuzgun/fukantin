package com.gundemgaming.fukantin.dto;

import com.gundemgaming.fukantin.model.Post;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {

    private Long id;

    @NotEmpty(
        message = "Category ismi bos birakilamaz."
    )
    @NotNull
    @Size(
            max = 30,
            message = "Category en fazla 30 karakter icermelidir."
    )
    private String category;

}
