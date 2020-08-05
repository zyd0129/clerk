package com.wind.clerk.oauth.service.impl;

import com.wind.clerk.oauth.dao.mapper.AuthorityMapper;
import com.wind.clerk.oauth.dao.entity.AuthorityDO;
import com.wind.clerk.oauth.model.AuthorityTree;
import com.wind.clerk.oauth.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    AuthorityMapper authorityMapper;

    @Override
    public Boolean update(AuthorityDO authorityDO) {
        LocalDateTime now = LocalDateTime.now();
        authorityDO.setGmtModified(now);
        return authorityMapper.update(authorityDO);
    }

    @Override
    public Boolean insert(AuthorityDO authorityDO) {
        LocalDateTime now = LocalDateTime.now();
        authorityDO.setGmtCreated(now);
        authorityDO.setGmtModified(now);
        return authorityMapper.insert(authorityDO);
    }

    @Override
    public List<AuthorityDO> query() {
        return authorityMapper.query();
    }

    @Override
    public AuthorityDO tree() {
        AuthorityTree authorityTree = new AuthorityTree(authorityMapper.query());
        return authorityTree.build();
    }

    @Override
    public Boolean delete(Integer id) {
        return authorityMapper.delete(id);
    }
}
