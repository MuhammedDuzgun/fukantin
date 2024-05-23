package com.gundemgaming.fukantin.service;


import com.gundemgaming.fukantin.dto.PostDto;

import java.util.List;

public interface IPostService {

    List<PostDto> getAllPosts();
    List<PostDto> getAllPostsWithCategory(Long categoryId);
    PostDto getPost(Long postId);
    PostDto createPost(PostDto postDto, Long userId);
    PostDto updatePost(PostDto postDto, Long postId);
    void deletePost(Long postId);

}
