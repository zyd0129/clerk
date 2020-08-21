package com.ts.clerk.oauth.controller;

import com.github.pagehelper.PageInfo;
import com.ts.clerk.common.response.ApiResponse;
import com.ts.clerk.oauth.dao.entity.UserDO;
import com.ts.clerk.oauth.pojo.query.PageQuery;
import com.ts.clerk.oauth.pojo.query.UserQuery;
import com.ts.clerk.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("users")
@RestController
public class UserControl {

    @Autowired
    UserService userService;

    @PostMapping("query")
    public ApiResponse<PageInfo<UserDO>> userBOList(@RequestBody PageQuery<UserQuery> pageQuery) {
        PageInfo<UserDO> userDOPageInfo = userService.queryByPage(pageQuery.getQuery(), pageQuery.getCurPage(), pageQuery.getPageSize());
        return ApiResponse.success(userDOPageInfo);
    }

    @PostMapping("add")
    public ApiResponse addUser(@RequestBody UserDO userDO) {
        userService.insert(userDO);
        return ApiResponse.success(userDO);
    }

    @GetMapping("get")
    public ApiResponse<UserDO> getUser(Integer id) {
        return ApiResponse.success(userService.getByIdWithRoles(id));
    }

    @PostMapping("modify")
    public ApiResponse modifyUser(@RequestBody UserDO userDO) {
        userDO.setPassword(null);
        userService.update(userDO);
        return ApiResponse.success();
    }

    @PostMapping("resetPassword")
    public ApiResponse resetPassword(@RequestBody UserDO userDO) throws Exception {
        userService.update(userDO);
        return ApiResponse.success();
    }

    @PostMapping("delete")
    public ApiResponse deleteUser(@RequestBody UserDO userDO) throws Exception {
        userService.delete(userDO.getId());
        return ApiResponse.success();
    }
}
