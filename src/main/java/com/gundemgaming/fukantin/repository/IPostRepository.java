package com.gundemgaming.fukantin.repository;

import com.gundemgaming.fukantin.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepository extends JpaRepository<Post, Long> {
}
