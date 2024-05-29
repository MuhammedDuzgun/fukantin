package com.gundemgaming.fukantin.dto;

import lombok.*;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String username;
    private List<PostDto> posts;
    private List<ReplyDto> replies;
    private UserDetailDto userDetail;
}
