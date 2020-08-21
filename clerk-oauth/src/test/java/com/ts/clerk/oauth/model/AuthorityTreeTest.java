package com.ts.clerk.oauth.model;

import com.ts.clerk.oauth.dao.entity.AuthorityDO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AuthorityTreeTest {

    @Test
    public void test() {
        List<AuthorityDO> authorityDOList = new ArrayList<>();
        authorityDOList.add(new AuthorityDO(1, "l1-1", 1, null));
        authorityDOList.add(new AuthorityDO(2, "l1-2", 3, null));
        authorityDOList.add(new AuthorityDO(3, "l1-3", 2, null));
        authorityDOList.add(new AuthorityDO(4, "l1-4", 4, null));
        authorityDOList.add(new AuthorityDO(5, "l2-1", 1, 1));
        authorityDOList.add(new AuthorityDO(6, "l2-2", 2, 1));
        authorityDOList.add(new AuthorityDO(7, "l2-3", 1, 2));
        authorityDOList.add(new AuthorityDO(8, "l3-1", 1, 7));
        AuthorityTree authorityTree = new AuthorityTree(authorityDOList);

        System.out.println(authorityTree.build());
    }
}