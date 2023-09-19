package com.monochrome.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SysLogAspect {

    @Pointcut("@annotation(com.monochrome.aop.annotation.SysLog)")
    public void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 逻辑开始时间
        long beginTime = System.currentTimeMillis();
        System.out.println("begin log: " + point.getSignature());
        // 执行方法
        Object result = point.proceed();

        //TODO ，保存日志，自己完善
        // saveLog(point,beginTime);

        System.out.println("end log: " + point.getSignature());
        System.out.println("end log cost: " + (System.currentTimeMillis() - beginTime));
        return result;
    }
}