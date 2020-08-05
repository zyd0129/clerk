package com.wind.clerk.oauth.service.bo;

import com.wind.clerk.oauth.dao.entity.AuthorityDO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data
public class AuthorityBO {
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

    public static AuthorityBO convertFromDo(AuthorityDO DO) {
        AuthorityBO BO = new AuthorityBO();
        BeanUtils.copyProperties(DO, BO);
        return BO;
    }

    public AuthorityDO convertToDO() {
        AuthorityDO authorityDO = new AuthorityDO();
        BeanUtils.copyProperties(this, authorityDO);
        LocalDateTime now = LocalDateTime.now();
        if (authorityDO.getId() == null) {
            authorityDO.setGmtCreated(now);
        }
        authorityDO.setGmtModified(now);
        return authorityDO;
    }
}
