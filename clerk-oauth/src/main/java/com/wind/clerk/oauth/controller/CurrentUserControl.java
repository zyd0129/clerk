package com.wind.clerk.oauth.controller;

import com.github.pagehelper.PageInfo;
import com.wind.clerk.oauth.dao.entity.UserDO;
import com.wind.clerk.oauth.pojo.form.ChangePasswordForm;
import com.wind.clerk.oauth.pojo.query.PageQuery;
import com.wind.clerk.oauth.pojo.query.UserQuery;
import com.wind.clerk.oauth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/currentUser")
@RestController
@AllArgsConstructor
public class CurrentUserControl {

    private UserService userService;

    @GetMapping("info")
    public UserDO getUser(Integer id) {
        //todo
        //获取当前用户id
        //查询数据库
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
}
