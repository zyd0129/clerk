package com.wind.clerk.oauth.dao.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DepartmentDO {
    private Integer id;
    private String name;
    private String displayName;
    private Integer managerId;

    private LocalDateTime gmtCreated;
    private LocalDateTime gmtModified;

    private UserDO manager;
    private List<UserDO> members;
}
