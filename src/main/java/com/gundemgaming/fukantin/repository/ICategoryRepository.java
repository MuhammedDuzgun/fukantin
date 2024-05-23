package com.gundemgaming.fukantin.repository;

import com.gundemgaming.fukantin.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {

}
