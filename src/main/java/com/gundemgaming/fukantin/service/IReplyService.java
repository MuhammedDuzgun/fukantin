package com.gundemgaming.fukantin.service;

import com.gundemgaming.fukantin.dto.ReplyDto;


import java.util.List;

public interface IReplyService {

    List<ReplyDto> getAllRepliesByPostId(Long postId);
    ReplyDto getReply(Long postId, Long replyId);
    ReplyDto createReply(ReplyDto replyDto, Long postId, Long userId);
    ReplyDto updateReply(ReplyDto replyDto, Long postId, Long replyId);
    void deleteReply(Long postId, Long replyId);

}
