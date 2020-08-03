package com.wind.clerk.oauth.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        return new User("zyd", "$2a$10$5NDp62dc6vO/nem1NnKnNuvCbRu9h3nrJtWQAl5hTfltGtJ1nSqEy", AuthorityUtils.createAuthorityList("ADMIN"));
    }
}
