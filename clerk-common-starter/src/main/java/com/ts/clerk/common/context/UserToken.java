package com.ts.clerk.common.context;

import lombok.Data;

import java.util.List;

@Data
public class UserToken {
    private Integer userId;
    private String userName;
    private Integer appId;
    private List<String> authorities;
    private List<String> appIds;
}
