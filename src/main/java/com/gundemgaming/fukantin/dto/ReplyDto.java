package com.gundemgaming.fukantin.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ReplyDto {

    private Long id;

    @NotEmpty(
            message = "Reply alani bos birakilamaz."
    )
    @Size(
            max = 50,
            message = "Reply en fazla 50 karakter icerebilir."
    )
    private String reply;

}
