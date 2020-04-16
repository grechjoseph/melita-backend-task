package com.grechjoseph.melitabackendtask.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@Component
public class ControllerAspect {

    @Before("anyController()")
    public void loggingBefore(JoinPoint joinPoint) {
        String methodName = getMethodName(joinPoint);
        String className = getClassName(joinPoint);
        Object[] args = joinPoint.getArgs();

        String parameters = Arrays.stream(args)
                .map(arg -> "arg " + arg + " - ")
                .collect(Collectors.joining());

        log.debug("called {} method of class {} with parameters {}", methodName, className, parameters);
    }

    @AfterReturning(pointcut = "anyController()", returning = "returnValue")
    public void loggingAfter(JoinPoint joinPoint, Object returnValue) {
        String methodName = getMethodName(joinPoint);
        String className = getClassName(joinPoint);

        log.debug("Returned {} method of class {} with value {}", methodName, className, returnValue);
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
