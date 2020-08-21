package com.ts.clerk.oauth.pojo.query;

import lombok.Data;

@Data
public class PageQuery<T> {
    private T query;
    private int curPage;
    private int pageSize;
}