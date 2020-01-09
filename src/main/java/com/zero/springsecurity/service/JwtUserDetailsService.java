package com.zero.springsecurity.service;

import com.zero.springsecurity.entity.UserInfo;
import com.zero.springsecurity.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoRepository.findByUsername(s);

        if (userInfo == null) {
            throw new UsernameNotFoundException("User with that name not found");
        }
        return new User(userInfo.getUsername(), userInfo.getPassword(), new ArrayList<>());
    }
}
