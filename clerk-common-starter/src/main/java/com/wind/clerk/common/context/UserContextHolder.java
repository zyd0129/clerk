package com.wind.clerk.common.context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wind.clerk.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class UserContextHolder {
    public static UserToken getCurrentUser() {
        UserToken userToken = null;
        ObjectMapper objectMapper = new ObjectMapper();
        //从Header中获取用户信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes == null) {
            log.error("cannot get servletRequestAttributes");
        } else {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            String userStr = request.getHeader("userToken");
            if (!StringUtils.isEmpty(userStr)) {
                try {
                    userToken = objectMapper.readValue(userStr, UserToken.class);
                } catch (JsonProcessingException e) {
                    log.error("userToken parse error");
                }
            }
        }
        if (userToken == null) {
            throw new BizException.BizAuthenticationException("Authentication required");
        }
        return userToken;
    }
}
