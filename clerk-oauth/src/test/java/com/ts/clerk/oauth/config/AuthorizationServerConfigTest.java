package com.ts.clerk.oauth.config;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jwt.JwtHelper;

import static org.junit.Assert.*;

//@SpringBootTest
public class AuthorizationServerConfigTest {

//    @Autowired
//    KeyProperties keyProperties;

    @Test
    public void main() {
        String accessToken = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzMSJdLCJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE1OTgyNDg0ODAsInVzZXJJZCI6MSwiYXV0aG9yaXRpZXMiOlsiL2NsZXJrLW9hdXRoL3JvbGVzL2RlbGV0ZSIsIi9jbGVyay1vYXV0aC9yb2xlcy9hZGQiLCIvY2xlcmstb2F1dGgvcm9sZXMvYWxsIiwiL2NsZXJrLW9hdXRoL3VzZXIvbW9kaWZ5IiwiL2NsZXJrLW9hdXRoL3JvbGVzL2dldCIsIi9jbGVyay1vYXV0aC9hdXRob3JpdGllcy90cmVlIiwiL3J1bGVzIiwiL2NsZXJrLW9hdXRoL3JvbGVzL21vZGlmeSIsIi9jbGVyay1vYXV0aC91c2Vycy9hZGQiLCIvcnVsZXMvYWRkIiwiL2NsZXJrLW9hdXRoL3VzZXJzL3F1ZXJ5IiwiL2NsZXJrLW9hdXRoL3JvbGVzL3F1ZXJ5Il0sImp0aSI6ImUwN2EyN2E3LTdhMWEtNDAzMi04MzFjLWRlZWMxNzZiOWY3MiIsImNsaWVudF9pZCI6ImMxIn0.EA2YAQpFbeb_a2CHJunA-omerqSSLN5063GPIIpt9VzfIw-JP2EmZaDk5rMo1ZZCWCtqxriAq-VZUNBgrb69pkEul_-FuosJSGPVwN0AuP4vtj7El_tON8ouvVuYPm4ZmbgwCR9_PLtwANEsznvtYBoP4HrUNLsPK-379l2yb61jl-ba3pdfQTJc8muEpCfWqTSmpAH-i8rbFWFDljZDKoA0nxJ_nIWYcWShYeg3VorhORpS3oUi7gUzEzXrlU3BTJeW2ck32BELfbTkgLcNXQn0pMueLxRepWTXjfJMI2uEec4mVe_Xzeiii8is2z4DY6cByFJqr69wb_wDGtsAyg";
        System.out.println(JwtHelper.decode(accessToken));
    }

    @Test
    public void test() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("a123456"));
    }
}