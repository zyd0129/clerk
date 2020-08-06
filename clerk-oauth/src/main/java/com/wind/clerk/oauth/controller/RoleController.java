package com.wind.clerk.oauth.controller;

import com.wind.clerk.oauth.dao.entity.RoleDO;
import com.wind.clerk.oauth.service.AuthorityService;
import com.wind.clerk.oauth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("test/roles")
@RestController
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("query")
    public List<RoleDO> listAuthorities() {
        return roleService.query();
    }

    @GetMapping("get")
    public RoleDO treeAuthorities(Integer id) {
        return roleService.getById(id);
    }

    @PostMapping("add")
    public RoleDO addAuthority(@RequestBody RoleDO roleDO) {
        roleService.insert(roleDO);
        return roleDO;
    }

    @PostMapping("modify")
    public Boolean modifyAuthority(@RequestBody RoleDO roleDO) {
        return roleService.update(roleDO);
    }

    @PostMapping("delete")
    public Boolean deleteAuthority(@RequestBody RoleDO roleDO) throws Exception {
        return roleService.delete(roleDO.getId());
    }
}
