package com.wind.clerk.oauth.service;

import com.wind.clerk.oauth.dao.entity.UserDO;

import java.util.List;

public interface UserService {
    List<UserDO> query();
}
