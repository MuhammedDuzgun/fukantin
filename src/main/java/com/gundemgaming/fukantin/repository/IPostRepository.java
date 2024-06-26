package com.gundemgaming.fukantin.repository;

import com.gundemgaming.fukantin.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCategoryId(Long categoryId);
}
