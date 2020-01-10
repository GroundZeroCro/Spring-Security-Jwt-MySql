package com.zero.springsecurity.authentication.repository;

import com.zero.springsecurity.authentication.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByUsername(String username);
    UserEntity findByUsername(String username);
}