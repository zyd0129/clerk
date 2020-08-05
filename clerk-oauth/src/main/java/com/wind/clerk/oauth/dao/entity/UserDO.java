package com.wind.clerk.oauth.dao.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDO {
    private Integer id;
    private String username;
    private String name;
    private String mobile;
    private String password;
    private Integer departmentId;
    private boolean expired;
    private boolean locked;
    private boolean credentialsExpired;
    private boolean enabled;
    /**
     * 分为平台用户和商户用户
     */
    private Integer userType;

    private LocalDateTime gmtCreated;
    private LocalDateTime gmtModified;

    private List<RoleDO> roles;
    private DepartmentDO department;
}
