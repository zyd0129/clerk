package com.wind.clerk.oauth.pojo.query;

import lombok.Data;


@Data
public class UserQuery {

    private String username;
    private String name;
    private String mobile;
    private Integer roleId;

}
