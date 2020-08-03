package com.wind.clerk.controller;

import org.apache.catalina.core.AsyncContextImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.AsyncContext;
import java.util.concurrent.FutureTask;

@RestController
public class Demo implements ApplicationContextAware {
    private ApplicationContext ctx;
    @Value("${user.name}")
    String name;


    @Value("${user.nickName}")
    String nickName;

    @GetMapping("hello")
    public String hello() {
//        FutureTask futureTask = new FutureTask(s -> s);
//        AsyncContext asyncContext = new AsyncContextImpl();
//        asyncContext.setTimeout();
        return "hello," + nickName;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}
