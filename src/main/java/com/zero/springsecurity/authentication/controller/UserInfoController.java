package com.zero.springsecurity.authentication.controller;

import com.zero.springsecurity.authentication.entity.UserInfo;
import com.zero.springsecurity.authentication.repository.UserInfoRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;

@RestController
class UserInfoController {

    private final UserInfoRepository userInfoRepository;

    public UserInfoController(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @PostMapping("/user")
    public Boolean create(@RequestBody UserInfo userInfo) throws ValidationException {

        if (userInfoRepository.existsByUsername(userInfo.getUsername())) {
            throw new ValidationException("User with that name already exists");
        }
        userInfo.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword()));

        userInfoRepository.save(userInfo);
        return true;
    }
}