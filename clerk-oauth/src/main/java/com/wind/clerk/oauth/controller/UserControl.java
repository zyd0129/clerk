package com.wind.clerk.oauth.controller;

import com.github.pagehelper.PageInfo;
import com.wind.clerk.oauth.dao.entity.UserDO;
import com.wind.clerk.oauth.pojo.form.ChangePasswordForm;
import com.wind.clerk.oauth.pojo.query.PageQuery;
import com.wind.clerk.oauth.pojo.query.UserQuery;
import com.wind.clerk.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("test/users")
@RestController
public class UserControl {

    @Autowired
    UserService userService;

    @PostMapping("query")
    public PageInfo<UserDO> userBOList(@RequestBody PageQuery<UserQuery> pageQuery) {
        return userService.queryByPage(pageQuery.getQuery(), pageQuery.getCurPage(), pageQuery.getPageSize());
    }

    @GetMapping("get")
    public UserDO getUser(Integer id) {
        return userService.getByIdWithRolesAndAuthorities(id);
    }

    @PostMapping("modify")
    public boolean modifyUser(@RequestBody UserDO userDO) {
        userDO.setPassword(null);
        return userService.update(userDO);
    }

    @PostMapping("changePassword")
    public boolean changePassword(@RequestBody ChangePasswordForm changePasswordForm) throws Exception {
        return userService.changePassword(changePasswordForm);
    }

    @PostMapping("resetPassword")
    public boolean resetPassword(@RequestBody UserDO userDO) throws Exception {
        return userService.update(userDO);
    }

    @PostMapping("add")
    public boolean addUser(@RequestBody UserDO userDO) {
        return userService.insert(userDO);
    }

    @PostMapping("delete")
    public Boolean deleteUser(@RequestBody UserDO userDO) throws Exception {
        return userService.delete(userDO.getId());
    }
}
