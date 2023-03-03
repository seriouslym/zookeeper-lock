package com.zjj.springpra.aop;

import com.zjj.springpra.Code;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.objenesis.ObjenesisHelper;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionAop {
    @Around("execution(* com.zjj.springpra..*.*(..))")
    public Object test(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Code response;
        try {
            return proceedingJoinPoint.proceed();
        } catch (Exception e) {
            response = new Code("错误", 500);
            System.out.println(response);
        }finally {
            System.out.println("共花费了 " + String.valueOf(System.currentTimeMillis() - startTime));
        }
        return response;
    }

}
