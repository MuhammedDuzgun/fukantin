package com.gundemgaming.fukantin.repository;

import com.gundemgaming.fukantin.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDetail extends JpaRepository<UserDetail, Long> {
}
