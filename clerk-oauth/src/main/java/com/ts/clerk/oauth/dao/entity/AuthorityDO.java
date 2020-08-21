package com.ts.clerk.oauth.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class AuthorityDO implements Serializable {
    private Integer id;
    private String name;
    private String displayName;
    private Integer order;
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

    private List<AuthorityDO> children;

    public AuthorityDO(Integer id, String name, Integer order, Integer parent) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.parent = parent;
    }
}
