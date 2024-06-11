package com.gundemgaming.fukantin.service.impl;

import com.gundemgaming.fukantin.dto.PostDto;
import com.gundemgaming.fukantin.dto.PostResponse;
import com.gundemgaming.fukantin.exception.FuKantinAPIException;
import com.gundemgaming.fukantin.exception.ResourceNotFoundException;
import com.gundemgaming.fukantin.model.Post;
import com.gundemgaming.fukantin.model.User;
import com.gundemgaming.fukantin.repository.ICategoryRepository;
import com.gundemgaming.fukantin.repository.IPostRepository;
import com.gundemgaming.fukantin.repository.IUserRepository;
import com.gundemgaming.fukantin.service.IPostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {

    private IPostRepository postRepository;
    private IUserRepository userRepository;
    private ICategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PostService(IPostRepository postRepository, IUserRepository userRepository, ICategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        //Sort
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        //create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        List<Post> postList = posts.getContent();

        List<PostDto> content = postList.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public List<PostDto> getAllPostsWithCategory(Long categoryId) {
        //check is category exists
        if(!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category", "categoryId", categoryId);
        }

        List<PostDto> posts = postRepository.findByCategoryId(categoryId)
                .stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return posts;
    }

    @Override
    public PostDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        //convert dto to entity
        Post postToCreate = modelMapper.map(postDto, Post.class);

        //Get current user
        User user = getCurrentAuthenticatedUser();

        //set user
        postToCreate.setUser(user);

        //set date
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String formattedDate = dateFormat.format(dateNow);
        postToCreate.setDate(formattedDate);

        //Save post
        Post newPost = postRepository.save(postToCreate);

        return modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        //check is post exists
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

        //get current user
        User user = getCurrentAuthenticatedUser();

        //check is post belongs to user
        if(!Objects.equals(post.getUser().getId(), user.getId())) {
            throw new FuKantinAPIException("This post doesn't belongs to current user", HttpStatus.BAD_REQUEST);
        }

        post.setTitle(postDto.getTitle());
        post.setPost(postDto.getPost());

        postRepository.save(post);

        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public void deletePost(Long postId) {
        //get post
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

        //get current user
        User user = getCurrentAuthenticatedUser();

        //check is post belongs to user
        if(!Objects.equals(post.getUser().getId(), user.getId())) {
            throw new FuKantinAPIException("This post doesn't belongs to current user", HttpStatus.BAD_REQUEST);
        }

        postRepository.delete(post);
    }

    //Get current user
    private User getCurrentAuthenticatedUser() {
        //Get user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        User user = userRepository.findByUsername(login).get();

        return user;
    }

}
