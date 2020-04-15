package com.grechjoseph.melitabackendtask.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ControllerAspect {

    @Before("anyController()")
    public void loggingBefore(JoinPoint joinPoint) {
        String methodName = getMethodName(joinPoint);
        String className = getClassName(joinPoint);
        Object[] args = joinPoint.getArgs();

        String parameters = "";
        for (int i = 0; i < args.length; i++) {
            parameters += "arg " + (i + 1) + ": " + args[i];
            parameters += " --- ";
        }

        log.debug("called {} method of class {} with parameters {}", methodName, className, parameters);
    }

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void anyController() {
    }

    private String getMethodName(JoinPoint joinPoint) {
        return MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName();
    }

    private String getClassName(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getName();
    }
}
