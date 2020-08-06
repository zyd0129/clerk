package com.wind.clerk.oauth.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDO {
    private Integer userId;
    private Integer roleId;
    private LocalDateTime gmtCreated;
    private LocalDateTime gmtModified;

    public UserRoleDO(Integer roleId, Integer userId) {
        this.roleId = roleId;
        this.userId = userId;
        LocalDateTime now = LocalDateTime.now();
        gmtCreated = now;
        gmtModified = now;
    }
}
