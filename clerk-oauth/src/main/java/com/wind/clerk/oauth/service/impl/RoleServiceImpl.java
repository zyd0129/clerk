package com.wind.clerk.oauth.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wind.clerk.common.context.UserContextHolder;
import com.wind.clerk.oauth.dao.entity.AuthorityDO;
import com.wind.clerk.oauth.dao.entity.RoleAuthorityDO;
import com.wind.clerk.oauth.dao.entity.RoleDO;
import com.wind.clerk.oauth.dao.mapper.RoleMapper;
import com.wind.clerk.oauth.pojo.query.RoleQuery;
import com.wind.clerk.oauth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    @Override
    @Transactional
    public Boolean update(RoleDO roleDO) {
        roleDO.setGmtModified(LocalDateTime.now());
        roleDO.setOperatorId(UserContextHolder.getCurrentUser().getUserId());
        roleMapper.update(roleDO);
        List<AuthorityDO> authorities = roleDO.getPermissions();
        if (authorities == null) {
            return true;
        }
        roleMapper.deleteRoleAuthorityByRoleId(roleDO.getId());
        if (authorities.size() > 0) {
            roleMapper.batchInsertRoleAuthority(authorities.stream().map(s -> new RoleAuthorityDO(roleDO.getId(), s.getId())).collect(Collectors.toList()));
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean insert(RoleDO roleDO) {
        LocalDateTime now = LocalDateTime.now();
        roleDO.setGmtCreated(now);
        roleDO.setGmtModified(now);
        roleDO.setOperatorId(UserContextHolder.getCurrentUser().getUserId());
        roleMapper.insert(roleDO);
        List<AuthorityDO> authorities = roleDO.getPermissions();
        if (authorities != null && authorities.size() > 0) {
            List<RoleAuthorityDO> roleAuthorityDOS = authorities.stream().map(s -> new RoleAuthorityDO(roleDO.getId(), s.getId())).collect(Collectors.toList());
            roleMapper.batchInsertRoleAuthority(roleAuthorityDOS);
        }
        return true;
    }

    @Override
    public RoleDO getById(Integer id) {
        return roleMapper.getById(id);
    }

    @Override
    public List<RoleDO> all() {
        return roleMapper.all();
    }

    @Override
    public PageInfo<RoleDO> queryByPage(RoleQuery query, int curPage, int pageSize) {
        PageHelper.startPage(curPage, pageSize);
        if (query == null) {
            query = new RoleQuery();
        }
        return new PageInfo<>(roleMapper.query(query));
    }

    @Override
    public Boolean delete(Integer id) throws Exception {
        //todo
        return true;
    }
}
