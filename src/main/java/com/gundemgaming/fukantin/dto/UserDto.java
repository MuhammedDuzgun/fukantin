package com.gundemgaming.fukantin.dto;

import com.gundemgaming.fukantin.model.Post;
import com.gundemgaming.fukantin.model.Reply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private List<Post> posts;
    private List<Reply> replies;
    private Long userDetailId;
}
