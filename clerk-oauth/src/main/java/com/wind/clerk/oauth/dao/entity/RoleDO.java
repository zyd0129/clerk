package com.wind.clerk.oauth.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class RoleDO implements Serializable {
    private Integer id;
    private String name;
    private String displayName;
    private LocalDateTime gmtCreated;
    private LocalDateTime gmtModified;

    private List<AuthorityDO> permissions;


    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        if(this==obj){
            return true;
        }
        if(obj instanceof RoleDO){
            RoleDO roleDO=(RoleDO)obj;
            return roleDO.id.equals(this.id);
        }
        return false;
    }
}
