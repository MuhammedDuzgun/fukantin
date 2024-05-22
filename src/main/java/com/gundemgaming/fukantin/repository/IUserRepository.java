package com.gundemgaming.fukantin.repository;

import com.gundemgaming.fukantin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
