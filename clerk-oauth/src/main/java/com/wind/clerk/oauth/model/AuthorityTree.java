package com.wind.clerk.oauth.model;

import com.wind.clerk.oauth.dao.entity.AuthorityDO;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorityTree {
    private List<AuthorityDO> authorityDOList;
    private AuthorityDO root;

    public AuthorityTree(List<AuthorityDO> authorityDOS) {
        this.authorityDOList = authorityDOS;
        root = new AuthorityDO();
        root.setName("root");
    }

    private List<AuthorityDO> getSubAuthorities(Integer parent) {
        List<AuthorityDO> children;
        if (parent != null) {
            children = authorityDOList.stream()
                    .filter(authorityDO -> parent.equals(authorityDO.getParent()))
                    .sorted(Comparator.comparingInt(AuthorityDO::getOrder))
                    .collect(Collectors.toList());
        } else {
            children = authorityDOList.stream()
                    .filter(authorityDO -> null == authorityDO.getParent())
                    .sorted(Comparator.comparingInt(AuthorityDO::getOrder))
                    .collect(Collectors.toList());
        }
        return children;
    }

    public AuthorityDO build() {
        addChildren(root);
        return root;
    }

    private void addChildren(AuthorityDO root) {
        List<AuthorityDO> subAuthorities = getSubAuthorities(root.getId());
        root.setChildren(subAuthorities);
        if (subAuthorities.size() > 0) {
            subAuthorities.forEach(this::addChildren);
        }
    }
}
