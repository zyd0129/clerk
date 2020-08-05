package com.wind.clerk.oauth.service.impl;

import com.wind.clerk.oauth.dao.mapper.UserMapper;
import com.wind.clerk.oauth.dao.entity.UserDO;
import com.wind.clerk.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<UserDO> query() {

        return userMapper.query();
    }
}
