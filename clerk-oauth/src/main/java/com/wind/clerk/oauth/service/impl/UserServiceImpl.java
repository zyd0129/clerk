package com.wind.clerk.oauth.service.impl;

import com.wind.clerk.oauth.dao.mapper.UserMapper;
import com.wind.clerk.oauth.dao.entity.UserDO;
import com.wind.clerk.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<UserDO> query() {

        return userMapper.query();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        return new User("zyd", "$2a$10$5NDp62dc6vO/nem1NnKnNuvCbRu9h3nrJtWQAl5hTfltGtJ1nSqEy", AuthorityUtils.createAuthorityList("ADMIN"));
    }
}
