package com.ts.clerk.oauth.controller;

import com.github.pagehelper.PageInfo;
import com.ts.clerk.common.response.ApiResponse;
import com.ts.clerk.oauth.pojo.query.PageQuery;
import com.ts.clerk.oauth.service.RoleService;
import com.ts.clerk.oauth.dao.entity.RoleDO;
import com.ts.clerk.oauth.pojo.query.RoleQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("roles")
@RestController
public class RoleController {
    @Autowired
    RoleService roleService;

    @PostMapping("query")
    public ApiResponse<PageInfo<RoleDO>> queryRole(@RequestBody PageQuery<RoleQuery> pageQuery) {
        PageInfo<RoleDO> roleDOPageInfo = roleService.queryByPage(pageQuery.getQuery(), pageQuery.getCurPage(), pageQuery.getPageSize());
        return ApiResponse.success(roleDOPageInfo);
    }

    @GetMapping("all")
    public ApiResponse<List<RoleDO>> allRole() {
        return ApiResponse.success(roleService.all());
    }

    @GetMapping("get")
    public ApiResponse<RoleDO> getRole(Integer id) {
        return ApiResponse.success(roleService.getById(id));
    }

    @PostMapping("add")
    public ApiResponse<RoleDO> addRole(@RequestBody RoleDO roleDO) {
        roleService.insert(roleDO);
        return ApiResponse.success(roleDO);
    }

    @PostMapping("modify")
    public ApiResponse modifyRole(@RequestBody RoleDO roleDO) {
        roleService.update(roleDO);
        return ApiResponse.success();
    }

    @PostMapping("delete")
    public ApiResponse deleteRole(@RequestBody RoleDO roleDO) throws Exception {
        roleService.delete(roleDO.getId());
        return ApiResponse.success();
    }
}
