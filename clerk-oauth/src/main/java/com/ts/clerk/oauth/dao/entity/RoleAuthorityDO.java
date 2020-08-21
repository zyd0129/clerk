package com.ts.clerk.oauth.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleAuthorityDO {
    private Integer roleId;
    private Integer authorityId;
    private LocalDateTime gmtCreated;
    private LocalDateTime gmtModified;

    public RoleAuthorityDO(Integer roleId, Integer authorityId) {
        this.roleId = roleId;
        this.authorityId = authorityId;
        LocalDateTime now = LocalDateTime.now();
        gmtCreated = now;
        gmtModified = now;
    }
}
