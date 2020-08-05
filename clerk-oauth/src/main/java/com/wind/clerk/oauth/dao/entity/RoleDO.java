package com.wind.clerk.oauth.dao.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RoleDO {
    private Integer id;
    private String name;
    private String displayName;
    private LocalDateTime gmtCreated;
    private LocalDateTime gmtModified;

    private List<AuthorityDO> authorities;
}
