package com.zero.springsecurity.authentication.repository;

import com.zero.springsecurity.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<User, Integer> {
    boolean existsByUsername(String username);
    User findByUsername(String username);
}