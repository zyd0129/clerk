package com.wind.clerk.oauth.service;


import com.wind.clerk.oauth.service.bo.AuthorityBO;

import java.util.List;

public interface AuthorityService {
    Boolean update(AuthorityBO authorityBO);
    Boolean insert(AuthorityBO authorityBO);
    List<AuthorityBO> query();
    Boolean delete(Integer id);
}
