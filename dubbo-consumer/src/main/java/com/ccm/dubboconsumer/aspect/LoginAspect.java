package com.ccm.dubboconsumer.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by chencm on 2018/12/4
 */
@Component
@Aspect
@Deprecated
public class LoginAspect {

    @Pointcut("@annotation(com.ccm.dubboconsumer.annotation.LoginCheck)")
    public void loginCheckAspect() {

    }

    @Around("loginCheckAspect()")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
        loginCheck();
        return joinPoint.proceed();
    }

    private void loginCheck(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
