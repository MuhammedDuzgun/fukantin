package com.gundemgaming.fukantin.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class PostDto {

    private Long id;

    @NotEmpty(
            message = "Title alani bos birakilamaz."
    )
    @Size(
            max = 25,
            message = "Title en fazla 25 karakter icerebilir."
    )
    private String title;

    @NotEmpty(
            message = "Post alani bos birakilamaz."
    )
    @Size(
            max = 250,
            message = "Post en fazla 250 karakter icerebilir."
    )
    private String post;

    private List<ReplyDto> replies;
    private Long categoryId;

}