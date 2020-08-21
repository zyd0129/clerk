package com.ts.clerk.oauth.service;


import com.github.pagehelper.PageInfo;
import com.ts.clerk.oauth.dao.entity.RoleDO;
import com.ts.clerk.oauth.pojo.query.RoleQuery;

import java.util.List;

public interface RoleService {
    Boolean update(RoleDO authorityDO);

    Boolean insert(RoleDO authorityDO);

    RoleDO getById(Integer id);

    List<RoleDO> all();

    PageInfo<RoleDO> queryByPage(RoleQuery query, int curPage, int pageSize);

    Boolean delete(Integer id) throws Exception;
}
