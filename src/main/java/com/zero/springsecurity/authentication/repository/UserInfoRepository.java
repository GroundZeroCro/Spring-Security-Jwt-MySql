package com.zero.springsecurity.authentication.repository;

import com.zero.springsecurity.authentication.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    boolean existsByUsername(String username);
    UserInfo findByUsername(String username);
}