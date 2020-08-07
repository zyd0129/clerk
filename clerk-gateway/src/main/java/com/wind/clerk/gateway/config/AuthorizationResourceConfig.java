package com.wind.clerk.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class AuthorizationResourceConfig extends ResourceServerConfigurerAdapter {
    //    @Autowired
//    TokenStore tokenStore;
    public static final String RESOURCE_ID = "clerk-gateway";

    public AuthorizationResourceConfig() {
        super();
    }
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.resourceId(RESOURCE_ID)
////                .tokenServices(remoteTokenServices())
////                .tokenStore(tokenStore) //jwt
//                .stateless(true);
//    }

    /**
     * 权限控制还需要配合 WebSecurityConfigurerAdapter
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
//        http
//                .authorizeRequests()
//                .antMatchers("/**").access("#oauth2.hasScope('all')")
//                .and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

//
//    @Bean
//    public RemoteTokenServices remoteTokenServices() {
//        RemoteTokenServices tokenServices = new RemoteTokenServices();
//        tokenServices.setCheckTokenEndpointUrl("http://check_token");
//        tokenServices.setClientId("http://check_token");
//        tokenServices.setClientSecret("http://check_token");
//
//        return tokenServices;
//
//    }
}
