package com.wind.clerk.oauth.util;

import com.wind.clerk.oauth.dao.entity.AuthorityDO;

import java.util.List;

public class Convert<S,T> {
//    default T copyProperties(S source){
//        getClass().getDeclaredMethod()
//    }

    public static AuthorityDO convertToAuthorityTree(List<AuthorityDO> authorityDOS) {
        AuthorityDO root = new AuthorityDO();
        return root;
    }
}
