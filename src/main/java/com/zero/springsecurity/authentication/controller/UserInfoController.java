package com.zero.springsecurity.authentication.controller;

import com.zero.springsecurity.authentication.entity.User;
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
    public Boolean create(@RequestBody User user) throws ValidationException {

        if (userInfoRepository.existsByUsername(user.getUsername())) {
            throw new ValidationException("User with that name already exists");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        userInfoRepository.save(user);
        return true;
    }
}