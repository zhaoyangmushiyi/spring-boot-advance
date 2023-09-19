package com.monochrome.aop.aop;

import com.monochrome.aop.annotation.RepeatSubmit;
import com.monochrome.aop.exception.RepeatSubmitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

// @Component
public class RepeatSubmitInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            //只拦截标注了@RepeatSubmit该注解
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //获取controller方法上标注的注解
            RepeatSubmit repeatSubmit =
                    AnnotationUtils.findAnnotation(handlerMethod.getMethod(), RepeatSubmit.class);
            //没有限制重复提交，直接跳过
            if (Objects.isNull(repeatSubmit)) {
                return true;
            }
            //todo 一个值，标志这个请求的唯一性，比如IP+userId+uri+请求参数
            String flag = "";
            //存在即返回false，不存在即返回true
            Boolean ifAbsent = stringRedisTemplate.opsForValue().setIfAbsent(flag, "",
                    repeatSubmit.seconds(), TimeUnit.SECONDS);
            if (ifAbsent != null && !ifAbsent) {
                //todo: 此处抛出异常，需要在全局异常解析器中捕获
                throw new RepeatSubmitException();
            }
        }
        return true;
    }
}