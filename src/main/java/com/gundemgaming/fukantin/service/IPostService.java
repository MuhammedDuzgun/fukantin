package com.gundemgaming.fukantin.service;


import com.gundemgaming.fukantin.dto.PostDto;
import com.gundemgaming.fukantin.dto.PostResponse;

import java.util.List;

public interface IPostService {

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    List<PostDto> getAllPostsWithCategory(Long categoryId);
    PostDto getPost(Long postId);
    PostDto createPost(PostDto postDto, Long userId);
    PostDto updatePost(PostDto postDto, Long postId);
    void deletePost(Long postId);

}
