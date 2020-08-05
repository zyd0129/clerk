package com.wind.clerk.oauth.controller;

import com.wind.clerk.oauth.dao.entity.AuthorityDO;
import com.wind.clerk.oauth.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("test/authorities")
@RestController
public class AuthorityController {
    @Autowired
    AuthorityService authorityService;

    @GetMapping("query")
    public List<AuthorityDO> listAuthorities() {
        return authorityService.query();
    }

    @GetMapping("tree")
    public AuthorityDO treeAuthorities() {
        return authorityService.tree();
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
