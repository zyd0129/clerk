package com.ts.clerk.oauth.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ts.clerk.common.context.UserContextHolder;
import com.ts.clerk.common.exception.BizEnum;
import com.ts.clerk.common.exception.BizException;
import com.ts.clerk.oauth.dao.entity.UserDO;
import com.ts.clerk.oauth.dao.entity.UserRoleDO;
import com.ts.clerk.oauth.dao.mapper.UserMapper;
import com.ts.clerk.oauth.pojo.query.UserQuery;
import com.ts.clerk.oauth.service.UserService;
import com.ts.clerk.oauth.dao.entity.RoleDO;
import com.ts.clerk.oauth.pojo.form.ChangePasswordForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired


    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<UserDO> query() {

        return userMapper.query(new UserQuery());
    }

    @Override
    public PageInfo<UserDO> queryByPage(UserQuery query, int curPage, int pageSize) {
        PageHelper.startPage(curPage, pageSize);
        if (query == null) {
            query = new UserQuery();
        }
        return new PageInfo<>(userMapper.query(query));
    }

    @Override
    @Transactional
    public Boolean update(UserDO userDO) {
        userDO.setGmtModified(LocalDateTime.now());
        userDO.setOperatorId(UserContextHolder.getCurrentUser().getUserId());
        String password = userDO.getPassword();
        if (!StringUtils.isEmpty(password)) {
            userDO.setPassword(passwordEncoder.encode(password));
        }
        userMapper.update(userDO);
        List<RoleDO> roles = userDO.getRoles();
        if (roles == null) {
            return true;
        }
        userMapper.deleteUserRoleByUserId(userDO.getId());
        if (roles.size() > 0) {
            userMapper.batchInsertUserRole(roles.stream().map(s -> new UserRoleDO(userDO.getId(), s.getId())).collect(Collectors.toList()));
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean insert(UserDO userDO) {
        LocalDateTime now = LocalDateTime.now();
        userDO.setGmtCreated(now);
        userDO.setGmtModified(now);
        userDO.setOperatorId(UserContextHolder.getCurrentUser().getUserId());
        String password = userDO.getPassword();
        if (!StringUtils.isEmpty(password)) {
            userDO.setPassword(passwordEncoder.encode(password));
        }
        userMapper.insert(userDO);
        List<RoleDO> roles = userDO.getRoles();
        if (roles != null && roles.size() > 0) {
            List<UserRoleDO> userRoleDOS = roles.stream().map(s -> new UserRoleDO(userDO.getId(), s.getId())).collect(Collectors.toList());
            userMapper.batchInsertUserRole(userRoleDOS);
        }
        return true;
    }

    @Override
    public UserDO getByIdWithRoles(Integer id) {
        return userMapper.getByIdWithRoles(id);
    }

    @Override
    public UserDO getByIdWithRolesAndAuthorities(Integer id) {
        return userMapper.getByIdWithRolesAndAuthorities(id);
    }

    @Override
    public void changePassword(ChangePasswordForm changePasswordForm) {
        Integer userId = UserContextHolder.getCurrentUser().getUserId();
        UserDO userDO = userMapper.getById(userId);
        if (userDO == null) {
            throw new BizException(BizEnum.USER_NOT_EXIST);
        }
        if (!passwordEncoder.matches(changePasswordForm.getOldPassword(), userDO.getPassword())) {
            throw new BizException(BizEnum.USER_PASSWORD_INCONSISTENCY);
        }
        UserDO newPasswordUserDO = new UserDO();
        newPasswordUserDO.setId(userId);
        newPasswordUserDO.setPassword(changePasswordForm.getNewPassword());
        update(newPasswordUserDO);
    }

    @Override
    public Boolean delete(Integer id) {
        return userMapper.delete(id);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO userDO = userMapper.getByUsernameWithRolesAndAuthorities(username);
        if (userDO == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return userDO;
    }
}
