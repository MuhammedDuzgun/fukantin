package com.gundemgaming.fukantin.service.impl;

import com.gundemgaming.fukantin.dto.ReplyDto;
import com.gundemgaming.fukantin.model.Post;
import com.gundemgaming.fukantin.model.Reply;
import com.gundemgaming.fukantin.model.User;
import com.gundemgaming.fukantin.repository.IPostRepository;
import com.gundemgaming.fukantin.repository.IReplyRepository;
import com.gundemgaming.fukantin.repository.IUserRepository;
import com.gundemgaming.fukantin.service.IReplyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<ReplyDto> replies = replyRepository.findByPostId(postId)
                .stream().map(reply -> modelMapper.map(reply, ReplyDto.class)).collect(Collectors.toList());
        return replies;
    }

    @Override
    public ReplyDto getReply(Long postId, Long replyId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(()-> new IllegalArgumentException("Boyle bir reply yok"));

        return modelMapper.map(reply, ReplyDto.class);
    }

    @Override
    public ReplyDto createReply(ReplyDto replyDto, Long postId, Long userId) {
        //convert replyDto to entity
        Reply reply = modelMapper.map(replyDto, Reply.class);

        //check post
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("Boyle bir post yok"));

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("Boyle bir user yok"));

        //set post & user
        reply.setUser(user);
        reply.setPost(post);

        //set date
        Date dateNow = new Date();
        reply.setDate(dateNow.toString());

        //save reply
        replyRepository.save(reply);

        return modelMapper.map(reply, ReplyDto.class);
    }

    @Override
    public ReplyDto updateReply(ReplyDto replyDto, Long postId, Long replyId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("Boyle bir post yok"));

        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(()-> new IllegalArgumentException("Boyle bir reply yok"));

        //is reply belongs to post
        if(reply.getPost().getId() != post.getId()) {
            throw new IllegalStateException("Bu reply bu posta ait degil");
        }

        reply.setReply(replyDto.getReply());

        Reply updatedReply = replyRepository.save(reply);

        return modelMapper.map(updatedReply, ReplyDto.class);
    }

    @Override
    public void deleteReply(Long postId, Long replyId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("Boyle bir post yok"));

        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(()-> new IllegalArgumentException("Boyle bir reply yok"));

        //is reply belongs to post
        if(reply.getPost().getId() != post.getId()) {
            throw new IllegalStateException("Bu reply bu posta ait degil");
        }

        replyRepository.delete(reply);
    }

}
