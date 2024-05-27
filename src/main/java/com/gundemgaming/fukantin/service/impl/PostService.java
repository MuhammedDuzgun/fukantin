package com.gundemgaming.fukantin.service.impl;

import com.gundemgaming.fukantin.dto.PostDto;
import com.gundemgaming.fukantin.model.Post;
import com.gundemgaming.fukantin.model.User;
import com.gundemgaming.fukantin.repository.IPostRepository;
import com.gundemgaming.fukantin.repository.IUserRepository;
import com.gundemgaming.fukantin.service.IPostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {

    private IPostRepository postRepository;
    private IUserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PostService(IPostRepository postRepository, IUserRepository userRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<PostDto> posts = postRepository.findAll()
                .stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return posts;
    }

    @Override
    public List<PostDto> getAllPostsWithCategory(Long categoryId) {
        List<PostDto> posts = postRepository.findByCategoryId(categoryId)
                .stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return posts;
    }

    @Override
    public PostDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Boyle bir post yok"));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto createPost(PostDto postDto, Long userId) {
        //convert dto to entity
        Post postToCreate = modelMapper.map(postDto, Post.class);

        //Get user
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("Boyle bir User yok"));

        //set user
        postToCreate.setUser(user);

        //set date
        Date dateNow = new Date();
        postToCreate.setDate(dateNow.toString());

        //Save post
        Post newPost = postRepository.save(postToCreate);

        return modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        //check is post exists
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Boyle bir post yok"));

        post.setTitle(postDto.getTitle());
        post.setPost(postDto.getPost());

        postRepository.save(post);

        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Boyle bir post yok"));
        postRepository.delete(post);
    }
}
