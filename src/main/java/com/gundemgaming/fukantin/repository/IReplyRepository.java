package com.gundemgaming.fukantin.repository;

import com.gundemgaming.fukantin.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReplyRepository extends JpaRepository<Reply, Long> {
}
