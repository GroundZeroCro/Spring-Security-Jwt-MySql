package com.zero.springsecurity.repository;

import com.zero.springsecurity.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    boolean existsByUsername(String username);
    UserInfo findByUsername(String username);
}