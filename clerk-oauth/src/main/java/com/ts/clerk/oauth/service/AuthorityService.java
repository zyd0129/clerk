package com.ts.clerk.oauth.service;


import com.ts.clerk.oauth.dao.entity.AuthorityDO;

import java.util.List;

public interface AuthorityService {
    Boolean update(AuthorityDO authorityDO);

    Boolean insert(AuthorityDO authorityDO);

    List<AuthorityDO> query();

    AuthorityDO tree();

    Boolean delete(Integer id) throws Exception;
}
