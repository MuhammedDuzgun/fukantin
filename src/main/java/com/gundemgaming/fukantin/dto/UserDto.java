package com.gundemgaming.fukantin.dto;

import com.gundemgaming.fukantin.model.Post;
import com.gundemgaming.fukantin.model.Reply;
import lombok.*;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String username;
    private List<Post> posts;
    private List<Reply> replies;
    private Long userDetailId;
}
