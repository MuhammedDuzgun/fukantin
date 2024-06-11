package com.gundemgaming.fukantin.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class PostDto {

    private Long id;

    @NotNull(message = "Title cannot be null")
    @NotEmpty(message = "Title cannot be empty")
    @Size(max = 25, message = "Title must contain maximum 25 characters")
    private String title;

    @NotNull(message = "Post cannot be null")
    @NotEmpty(message = "Post cannot be empty.")
    @Size(max = 250, message = "Post must contain maximum 250 characters")
    private String post;

    private String date;
    private List<ReplyDto> replies;
    private Long categoryId;
    private Long userId;

}
