package com.wind.clerk.oauth.service.impl;

import com.wind.clerk.oauth.dao.UserMapper;
import com.wind.clerk.oauth.service.UserService;
import com.wind.clerk.oauth.service.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<UserBO> query() {

        return userMapper.query().stream().map(UserBO::convertFromDo).collect(Collectors.toList());
    }
}
