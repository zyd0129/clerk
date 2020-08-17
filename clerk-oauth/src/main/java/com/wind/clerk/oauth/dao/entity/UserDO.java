package com.wind.clerk.oauth.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDO implements UserDetails {
    private Integer id;
    private String username;
    private String name;
    private String mobile;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private boolean expired;
    @JsonIgnore
    private boolean locked;
    @JsonIgnore
    private boolean credentialsExpired;
    @JsonIgnore
    private boolean enabled;
    /**
     * 分为平台用户和商户用户
     */
    private Integer userType;

    private LocalDateTime gmtCreated;
    private LocalDateTime gmtModified;

    private List<RoleDO> roles;
    private List<AuthorityDO> permissions;

    @JsonIgnore
    private Integer operatorId;

    private UserDO operator;

    @JsonIgnore
    public Integer getOperatorId() {
        return operatorId;
    }

    @JsonProperty
    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //权限和资源路径一一对应，这里返还url,简化网关鉴权
        return permissions.stream().map(s -> new SimpleGrantedAuthority(s.getResourceUrl())).collect(Collectors.toList());
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
