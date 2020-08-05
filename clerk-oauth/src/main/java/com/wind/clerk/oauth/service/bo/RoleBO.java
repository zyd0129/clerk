package com.wind.clerk.oauth.service.bo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RoleBO {
    private Integer id;
    private String name;
    private String displayName;
    private LocalDateTime gmtCreated;
    private LocalDateTime gmtModified;

    private List<AuthorityBO> authorities;
}
