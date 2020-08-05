package com.wind.clerk.oauth.controller.req;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuthorityReq {
    private Integer id;
    private String name;
    private String displayName;
    /**
     * 网关相对路径
     */
    private String resourceUrl;
    /**
     * 上级目录，目录也是一种资源，也可以对应一个resource url
     */
    private Integer parent;
    private LocalDateTime gmtCreated;
    private LocalDateTime gmtModified;
}
