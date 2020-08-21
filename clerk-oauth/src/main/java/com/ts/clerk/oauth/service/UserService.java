package com.ts.clerk.oauth.service;

import com.github.pagehelper.PageInfo;
import com.ts.clerk.oauth.dao.entity.UserDO;
import com.ts.clerk.oauth.pojo.query.UserQuery;
import com.ts.clerk.oauth.pojo.form.ChangePasswordForm;

import java.util.List;

public interface UserService {
    List<UserDO> query();

    PageInfo<UserDO> queryByPage(UserQuery query, int curPage, int pageSize);

    Boolean update(UserDO userDO);

    Boolean insert(UserDO userDO);

    UserDO getByIdWithRoles(Integer id);

    UserDO getByIdWithRolesAndAuthorities(Integer id);

    void changePassword(ChangePasswordForm changePasswordForm);

    Boolean delete(Integer id);
}
