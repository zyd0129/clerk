package com.ts.clerk.oauth.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonIgnore
    private Integer operatorId;
    private UserDO operator;
    private List<AuthorityDO> permissions;

    @JsonIgnore
    public Integer getOperatorId() {
        return operatorId;
    }

    @JsonProperty
    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }
}
