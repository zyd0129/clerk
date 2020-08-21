package com.ts.clerk.gateway.filter;

import com.nimbusds.jose.JWSObject;
import com.ts.clerk.common.context.UserToken;
import org.junit.Test;

import java.text.ParseException;

public class AuthGlobalFilterTest {

    @Test
    public void test() throws ParseException {
//        DecodedJWT jwt = JWT.decode(token);
        String realToken = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzMSJdLCJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE1OTczMjUxOTUsInVzZXJJZCI6MSwiYXV0aG9yaXRpZXMiOlsiL3J1bGVzL2FkZCIsIi9oZWxsbyJdLCJqdGkiOiJlYTI1NjgyMS05YTc0LTRlOGEtYTdjMS1mNzNmYTc3NGZhZjAiLCJjbGllbnRfaWQiOiJjMSJ9.A7BUfjBfIX6VAgG7cdEgt4wV-1ipUIl2fjz3TWPKYI00Gqy27-ENLWhWp1KtwDdqmCvyEnhdheTh8_EWXogPz-kfz1SjJditUCCObMYNz3YnVCCMOmrdNJQGTmT5L7xP9KtVTEFz5aznYP5VfwCfET4Ehkpq4WJM_v09ZVXDv0oig-Znu5G-59hZWqIJVjqtS1b5F5CWEIGmewd8QMSQp---dvwRI_O7vSuVJqLK6O06cSjD21RR0gaiK4TBHZMcoMz0lp-wKkJZfrQPRB7NYaIE35PrOsfUN81JWhL6xKL1xBrGs6NFDiBrqS-sM4K0-2JtNAIiHktBUhSHIcgwKw";
        JWSObject jwsObject = JWSObject.parse(realToken);
        UserToken userToken = new UserToken();
        String userStr = jwsObject.getPayload().toString();
        System.out.println(userStr);
    }
}