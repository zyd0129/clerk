package com.wind.clerk.oauth.service.bo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DepartmentBO {
    private Integer id;
    private String name;
    private String displayName;
    private Integer managerId;

    private LocalDateTime gmtCreated;
    private LocalDateTime gmtModified;

    private UserBO manager;
    private List<UserBO> members;
}
