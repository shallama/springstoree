package com.example.springstore.repository;

import com.example.springstore.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
/**
 *  Repository for work with user DB table
 *  @author tagir
 *  @since 15.01.2022
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
