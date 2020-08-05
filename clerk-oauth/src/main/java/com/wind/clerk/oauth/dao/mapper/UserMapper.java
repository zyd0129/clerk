package com.wind.clerk.oauth.dao.mapper;

import com.wind.clerk.oauth.dao.entity.UserDO;

import java.util.List;

public interface UserMapper {
    List<UserDO> query();
}
