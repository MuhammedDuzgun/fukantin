package com.gundemgaming.fukantin.repository;

import com.gundemgaming.fukantin.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findByPostId(Long postId);

}
