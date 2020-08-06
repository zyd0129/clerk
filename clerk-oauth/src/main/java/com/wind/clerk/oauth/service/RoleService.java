package com.wind.clerk.oauth.service;


import com.wind.clerk.oauth.dao.entity.RoleDO;

import java.util.List;

public interface RoleService {
    Boolean update(RoleDO authorityDO);
    Boolean insert(RoleDO authorityDO);
    RoleDO getById(Integer id);
    List<RoleDO> query();
    Boolean delete(Integer id) throws Exception;
}
