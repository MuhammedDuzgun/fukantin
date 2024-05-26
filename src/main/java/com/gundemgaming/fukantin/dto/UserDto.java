package com.gundemgaming.fukantin.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String username;
    private List<PostDto> posts;
    private List<ReplyDto> replies;
    private UserDetailDto userDetail;
    private Set<RoleDto> roles;
}
