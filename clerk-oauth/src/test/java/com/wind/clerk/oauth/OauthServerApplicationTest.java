package com.wind.clerk.oauth;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

public class OauthServerApplicationTest {

    @Test
    public void test() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://www.baidu.com", String.class);
        System.out.println(forEntity.getBody());
    }

}