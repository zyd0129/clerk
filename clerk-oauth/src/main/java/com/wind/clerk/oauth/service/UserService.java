package com.wind.clerk.oauth.service;

import com.wind.clerk.oauth.service.bo.UserBO;

import java.util.List;

public interface UserService {
    List<UserBO> query();
}
