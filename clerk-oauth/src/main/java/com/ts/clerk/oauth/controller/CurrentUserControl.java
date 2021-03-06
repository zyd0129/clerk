package com.ts.clerk.oauth.controller;

import com.ts.clerk.common.context.UserContextHolder;
import com.ts.clerk.common.context.UserToken;
import com.ts.clerk.common.response.ApiResponse;
import com.ts.clerk.oauth.dao.entity.UserDO;
import com.ts.clerk.oauth.pojo.form.ChangePasswordForm;
import com.ts.clerk.oauth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("current")
@RestController
@AllArgsConstructor
public class CurrentUserControl {

    private UserService userService;

    @GetMapping("info")
    public ApiResponse<UserDO> getUser() {
        UserToken currentUser = UserContextHolder.getCurrentUser();
        UserDO userDO = userService.getByIdWithRolesAndAuthorities(currentUser.getUserId());
        return ApiResponse.success(userDO);
    }

//    @PostMapping("modify")
//    public boolean modifyUser(@RequestBody UserDO userDO) {
//        userDO.setPassword(null);
//        return userService.update(userDO);
//    }

    @PostMapping("changePassword")
    public ApiResponse changePassword(@RequestBody ChangePasswordForm changePasswordForm) throws Exception {
        userService.changePassword(changePasswordForm);
        return ApiResponse.success();
    }
}
