package com.wind.clerk.oauth.pojo.query;

import lombok.Data;

import javax.validation.Valid;

@Data
public class PageQuery<T> {
    @Valid
    private T query;
    private int curPage;
    private int pageSize;
}