package com.gundemgaming.fukantin.repository;

import com.gundemgaming.fukantin.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDetailRepository extends JpaRepository<UserDetail, Long> {
}
