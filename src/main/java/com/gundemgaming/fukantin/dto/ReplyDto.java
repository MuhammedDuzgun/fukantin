package com.gundemgaming.fukantin.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReplyDto {

    private Long id;

    @NotNull(message = "Reply cannot be null")
    @NotEmpty(message = "Reply cannot be empty")
    @Size(max = 50, message = "Reply must contain maximum 50 characters")
    private String reply;

    private String date;
    private Long postId;
    private Long userId;

}
