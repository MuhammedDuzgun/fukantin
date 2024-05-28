package com.gundemgaming.fukantin.controller;

import com.gundemgaming.fukantin.dto.ReplyDto;
import com.gundemgaming.fukantin.service.IReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReplyController {

    private IReplyService replyService;

    @Autowired
    public ReplyController(IReplyService replyService) {
        this.replyService = replyService;
    }

    @GetMapping("/posts/{id}/replies")
    public ResponseEntity<List<ReplyDto>> getAllRepliesByPostId(@PathVariable(name = "id") Long postId) {
        return ResponseEntity.ok(replyService.getAllRepliesByPostId(postId));
    }

    @GetMapping("/posts/{postId}/replies/{replyId}")
    public ResponseEntity<ReplyDto> getReply(@PathVariable(name = "postId") Long postId,
                                             @PathVariable(name = "replyId") Long replyId) {
        return ResponseEntity.ok(replyService.getReply(postId, replyId));
    }

    @PostMapping("/posts/{postId}/replies/{userId}")
    public ResponseEntity<ReplyDto> createReply(@RequestBody ReplyDto replyDto,
                                                @PathVariable(name = "postId") Long postId,
                                                @PathVariable(name = "userId") Long userId) {
        ReplyDto createdReply = replyService.createReply(replyDto, postId, userId);
        return new ResponseEntity<>(createdReply, HttpStatus.CREATED);
    }

    @PutMapping("/posts/{postId}/replies/{replyId}")
    public ResponseEntity<ReplyDto> updateReply(@RequestBody ReplyDto replyDto,
                                                @PathVariable(name = "postId") Long postId,
                                                @PathVariable(name = "replyId") Long replyId) {
       ReplyDto updatedReply = replyService.updateReply(replyDto, postId, replyId);
       return new ResponseEntity<>(updatedReply, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/replies/{replyId}")
    public ResponseEntity<String> deleteReply(@PathVariable(name = "postId") Long postId,
                                              @PathVariable(name = "replyId") Long replyId) {
        replyService.deleteReply(postId, replyId);
        return ResponseEntity.ok("Reply deleted successfuly");
    }

}