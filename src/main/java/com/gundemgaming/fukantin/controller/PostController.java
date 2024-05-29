package com.gundemgaming.fukantin.controller;

import com.gundemgaming.fukantin.dto.PostDto;
import com.gundemgaming.fukantin.service.IPostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {

    private IPostService postService;

    @Autowired
    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("category/{id}")
    public ResponseEntity<List<PostDto>> getAllPostsWithCategory(@PathVariable(name = "id") Long categoryId) {
        return ResponseEntity.ok(postService.getAllPostsWithCategory(categoryId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable(name = "id") Long postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @PostMapping("/{id}")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,
                                              @PathVariable(name = "id") Long userId) {
         PostDto createdPost = postService.createPost(postDto, userId);
         return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,
                                              @PathVariable(name = "id") Long postId) {
         return ResponseEntity.ok(postService.updatePost(postDto, postId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("Post Deleted Successfuly");
    }

}
