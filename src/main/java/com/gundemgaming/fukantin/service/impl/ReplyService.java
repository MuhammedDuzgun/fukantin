package com.gundemgaming.fukantin.service.impl;

import com.gundemgaming.fukantin.dto.ReplyDto;
import com.gundemgaming.fukantin.exception.FuKantinAPIException;
import com.gundemgaming.fukantin.exception.ResourceNotFoundException;
import com.gundemgaming.fukantin.model.Post;
import com.gundemgaming.fukantin.model.Reply;
import com.gundemgaming.fukantin.model.User;
import com.gundemgaming.fukantin.repository.IPostRepository;
import com.gundemgaming.fukantin.repository.IReplyRepository;
import com.gundemgaming.fukantin.repository.IUserRepository;
import com.gundemgaming.fukantin.service.IReplyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReplyService implements IReplyService {

    private IReplyRepository replyRepository;
    private IPostRepository postRepository;
    private IUserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ReplyService(IReplyRepository replyRepository, IPostRepository postRepository, IUserRepository userRepository, ModelMapper modelMapper) {
        this.replyRepository = replyRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ReplyDto> getAllRepliesByPostId(Long postId) {
        //check is post exists
        if(!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("Post", "postId", postId);
        }

        List<ReplyDto> replies = replyRepository.findByPostId(postId)
                .stream().map(reply -> modelMapper.map(reply, ReplyDto.class)).collect(Collectors.toList());
        return replies;
    }

    @Override
    public ReplyDto getReply(Long postId, Long replyId) {
        //check is post exists
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));

        //check is reply exists
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(()-> new ResourceNotFoundException("Reply", "replyId", replyId));

        //check is reply belongs to post
        if(reply.getPost().getId() != post.getId()) {
            throw new FuKantinAPIException("Reply doesn't belongs to Post", HttpStatus.BAD_REQUEST);
        }

        return modelMapper.map(reply, ReplyDto.class);
    }

    @Override
    public ReplyDto createReply(ReplyDto replyDto, Long postId) {
        //convert replyDto to entity
        Reply reply = modelMapper.map(replyDto, Reply.class);

        //check post
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));

        //check user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        User user = userRepository.findByUsername(login).get();


        //set post & user
        reply.setUser(user);
        reply.setPost(post);

        //set date
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String formattedDate = dateFormat.format(dateNow);
        reply.setDate(formattedDate);

        //save reply
        replyRepository.save(reply);

        return modelMapper.map(reply, ReplyDto.class);
    }

    @Override
    public ReplyDto updateReply(ReplyDto replyDto, Long postId, Long replyId) {
        //check is post exists
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));

        //check is reply exists
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(()-> new ResourceNotFoundException("Reply", "replyId", replyId));

        //is reply belongs to post
        if(reply.getPost().getId() != post.getId()) {
            throw new FuKantinAPIException("Reply doesn't belongs to Post", HttpStatus.BAD_REQUEST);
        }

        reply.setReply(replyDto.getReply());

        Reply updatedReply = replyRepository.save(reply);

        return modelMapper.map(updatedReply, ReplyDto.class);
    }

    @Override
    public void deleteReply(Long postId, Long replyId) {
        //check is post exists
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));

        //check is reply exists
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(()-> new ResourceNotFoundException("Reply", "replyId", replyId));

        //is reply belongs to post
        if(reply.getPost().getId() != post.getId()) {
            throw new FuKantinAPIException("Reply doesn't belongs to Post", HttpStatus.BAD_REQUEST);
        }

        replyRepository.delete(reply);
    }

}
