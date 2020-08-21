package com.ts.clerk.oauth.util;

import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ConvertTest {

    public void method1(String ss) {

    }
    public void method1(String ss,String s) {

    }

    @Test
    public void test() throws NoSuchMethodException {
        List<String> ar = new ArrayList<>();
        TypeVariable<? extends Class<? extends List>>[] typeParameters = ar.getClass().getTypeParameters();
        System.out.println(typeParameters);
    }
}