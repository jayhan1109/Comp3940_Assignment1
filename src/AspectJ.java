package com.example.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AspectJ {

    @Before("   call(String com.google.gson.Gson.toJson(Object)) " +
            "&& !within(com.example.aspects..*)")
    public void beforeToJson() {
        System.out.println("About to call toJson method");
    }

    @After("   call(String com.google.gson.Gson.toJson(Object)) " +
            "&& !within(com.example.aspects..*)")
    public void afterToJson() {
        System.out.println("toJson method just called");
    }

    @Before("   call(String java.lang.System.getProperty(String)) " +
            "&& !within(com.example.aspects..*)")
    public void beforeOS() {
        System.out.println("About to call OS method");
    }

    @After("   call(String java.lang.System.getProperty(String)) " +
            "&& !within(com.example.aspects..*)")
    public void afterOS() {
        System.out.println("OS method just called");
    }
}