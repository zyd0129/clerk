package com.wind.clerk.oauth.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions.stream().map(s -> new SimpleGrantedAuthority(s.getName())).collect(Collectors.toList());
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
}
