package com.wind.clerk.oauth.service.impl;

import com.wind.clerk.oauth.dao.AuthorityMapper;
import com.wind.clerk.oauth.service.AuthorityService;
import com.wind.clerk.oauth.service.bo.AuthorityBO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    AuthorityMapper authorityMapper;
    @Override
    public Boolean update(AuthorityBO authorityBO) {
        return authorityMapper.update(authorityBO.convertToDO());
    }

    @Override
    public Boolean insert(AuthorityBO authorityBO) {
        return authorityMapper.insert(authorityBO.convertToDO());
    }

    @Override
    public List<AuthorityBO> query() {
        return authorityMapper.query().stream().map(AuthorityBO::convertFromDo).collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Integer id) {
        return authorityMapper.delete(id);
    }
}
