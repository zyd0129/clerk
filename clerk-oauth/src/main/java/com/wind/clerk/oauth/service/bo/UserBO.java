package com.wind.clerk.oauth.service.bo;

import com.wind.clerk.oauth.dao.entity.UserDO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserBO {
    private Integer id;
    private String username;
    private String name;
    private String mobile;
    private String password;
    private Integer departmentId;
    private boolean expired;
    private boolean locked;
    private boolean credentialsExpired;
    private boolean enabled;
    private Integer userType;

    private LocalDateTime gmtCreated;
    private LocalDateTime gmtModified;

    private List<RoleBO> roles;
    private DepartmentBO department;


    public static UserBO convertFromDo(UserDO userDO) {
        UserBO userBO = new UserBO();
        BeanUtils.copyProperties(userDO, userBO);
        return userBO;
    }
}
