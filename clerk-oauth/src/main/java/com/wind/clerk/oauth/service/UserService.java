package com.wind.clerk.oauth.service;

import com.github.pagehelper.PageInfo;
import com.wind.clerk.oauth.dao.entity.UserDO;
import com.wind.clerk.oauth.pojo.form.ChangePasswordForm;
import com.wind.clerk.oauth.pojo.query.UserQuery;

import java.util.List;

public interface UserService {
    List<UserDO> query();
    PageInfo<UserDO> queryByPage(UserQuery query, int curPage, int pageSize);
    Boolean update(UserDO userDO);
    Boolean insert(UserDO userDO);
    UserDO getByIdWithRoles(Integer id);
    UserDO getByIdWithRolesAndAuthorities(Integer id);

    boolean changePassword(ChangePasswordForm changePasswordForm) throws Exception;

    Boolean delete(Integer id);
//    UserDO getByUsernameWithRolesAndAuthorities(String username);
}
