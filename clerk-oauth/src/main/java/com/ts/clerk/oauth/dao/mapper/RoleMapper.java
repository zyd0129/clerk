package com.ts.clerk.oauth.dao.mapper;


import com.ts.clerk.oauth.dao.entity.RoleAuthorityDO;
import com.ts.clerk.oauth.dao.entity.RoleDO;
import com.ts.clerk.oauth.pojo.query.RoleQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    Boolean update(RoleDO authority);

    Boolean insert(RoleDO authority);

    List<RoleDO> all();

    List<RoleDO> query(RoleQuery userQuery);

    Boolean delete(@Param("id") Integer id);

    RoleDO getById(@Param("id") Integer id);

    void batchInsertRoleAuthority(List<RoleAuthorityDO> roleAuthorityDOList);

    void deleteRoleAuthorityByRoleId(Integer roleId);
}
