package com.ts.clerk.oauth.config;

import com.ts.clerk.oauth.dao.entity.UserDO;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomUseTokenConverter extends DefaultUserAuthenticationConverter {
    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String, Object> response = (Map<String, Object>) super.convertUserAuthentication(authentication);
        UserDO principal = (UserDO) authentication.getPrincipal();
        response.put("userId", principal.getId());
        return response;
    }
}
