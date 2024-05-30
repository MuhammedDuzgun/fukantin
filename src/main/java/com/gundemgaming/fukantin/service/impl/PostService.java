package com.gundemgaming.fukantin.service.impl;

import com.gundemgaming.fukantin.dto.PostDto;
import com.gundemgaming.fukantin.dto.PostResponse;
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
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
    public PostDto createPost(PostDto postDto, Long userId) {
        //convert dto to entity
        Post postToCreate = modelMapper.map(postDto, Post.class);

        //Get user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User" , "userId", userId));

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

        post.setTitle(postDto.getTitle());
        post.setPost(postDto.getPost());

        postRepository.save(post);

        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        postRepository.delete(post);
    }
}
