package com.gundemgaming.fukantin.repository;

import com.gundemgaming.fukantin.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(String role);

}
