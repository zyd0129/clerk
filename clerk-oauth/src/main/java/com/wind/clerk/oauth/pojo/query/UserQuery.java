package com.wind.clerk.oauth.pojo.query;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserQuery {

    private String username;
    private String name;
    private String mobile;
    private Integer roleId;

}
