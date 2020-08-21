package com.ts.clerk.oauth.dao.mapper;


import com.ts.clerk.oauth.dao.entity.AuthorityDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthorityMapper {
    Boolean update(AuthorityDO authority);

    Boolean insert(AuthorityDO authority);

    List<AuthorityDO> query();

    Boolean delete(@Param("id") Integer id);

    Integer countOfRelatedRoles(@Param("authorityId") Integer authorityId);
}
