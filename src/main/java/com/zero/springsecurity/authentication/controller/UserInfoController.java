package com.zero.springsecurity.authentication.controller;

import com.zero.springsecurity.authentication.model.UserEntity;
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
    public Boolean create(@RequestBody UserEntity userEntity) throws ValidationException {

        if (userInfoRepository.existsByUsername(userEntity.getUsername())) {
            throw new ValidationException("User with that name already exists");
        }
        userEntity.setPassword(new BCryptPasswordEncoder().encode(userEntity.getPassword()));

        userInfoRepository.save(userEntity);
        return true;
    }
}