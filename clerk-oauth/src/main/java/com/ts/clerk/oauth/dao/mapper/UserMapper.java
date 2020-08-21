package com.ts.clerk.oauth.dao.mapper;

import com.ts.clerk.oauth.dao.entity.UserDO;
import com.ts.clerk.oauth.dao.entity.UserRoleDO;
import com.ts.clerk.oauth.pojo.query.UserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<UserDO> query(UserQuery userQuery);

    Boolean update(UserDO userDO);

    Boolean insert(UserDO userDO);

    UserDO getById(Integer id);

    UserDO getByIdWithRoles(Integer id);

    UserDO getByIdWithRolesAndAuthorities(Integer id);

    UserDO getByUsernameWithRolesAndAuthorities(@Param("username") String username);

    void batchInsertUserRole(List<UserRoleDO> userRoleDOS);

    void deleteUserRoleByUserId(@Param("userId") Integer userId);

    Boolean delete(Integer id);
}
