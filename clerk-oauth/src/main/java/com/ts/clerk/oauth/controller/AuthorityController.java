package com.ts.clerk.oauth.controller;

import com.ts.clerk.common.response.ApiResponse;
import com.ts.clerk.oauth.dao.entity.AuthorityDO;
import com.ts.clerk.oauth.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("authorities")
@RestController
public class AuthorityController {
    @Autowired
    AuthorityService authorityService;

    @GetMapping("query")
    public List<AuthorityDO> listAuthorities() {
        return authorityService.query();
    }

    @GetMapping("tree")
    public ApiResponse<AuthorityDO> treeAuthorities() {
        return ApiResponse.success(authorityService.tree());
    }

    @PostMapping("add")
    public AuthorityDO addAuthority(@RequestBody AuthorityDO authorityDO) {
        authorityService.insert(authorityDO);
        return authorityDO;
    }

    @PostMapping("modify")
    public Boolean modifyAuthority(@RequestBody AuthorityDO authorityDO) {
        return authorityService.update(authorityDO);
    }

    @PostMapping("delete")
    public Boolean deleteAuthority(@RequestBody AuthorityDO authorityDO) throws Exception {
        return authorityService.delete(authorityDO.getId());
    }
}
