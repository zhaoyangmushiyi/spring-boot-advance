package com.monochrome.interceptor.config;

import com.monochrome.interceptor.exception.RepeatSubmitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 拦截重复请求
 * @author monochrome
 * @date 2023/1/17
 */
@Component
public class RepeatSubmitInterceptor implements HandlerInterceptor {

    /**
     * Redis API
     */
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 判断条件仅仅是用了uri，实际开发中根据实际情况组合一个唯一识别的条件。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            // 只拦截标注了@RepeatSubmit该注解
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 标注在方法上的@RepeatSubmit
            RepeatSubmit repeatSubmitByMethod =
                    AnnotationUtils.findAnnotation(handlerMethod.getMethod(), RepeatSubmit.class);
            // 标注在Controller类上的@RepeatSubmit
            RepeatSubmit repeatSubmitByClass =
                    AnnotationUtils.findAnnotation(handlerMethod.getMethod().getDeclaringClass(), RepeatSubmit.class);
            // 没有限制重复提交，跳过
            if (Objects.isNull(repeatSubmitByMethod) && Objects.isNull(repeatSubmitByClass)) {
                return true;
            }
            // todo: 组合判断条件，这里仅仅是演示，实际项目中根据架构组合条件
            // 请求的URI
            String uri = request.getRequestURI();
            // redis中存在即返回false，不存在即返回true
            // 注意:标注在方法上的超时时间会覆盖掉类上的时间
            Boolean ifAbsent = stringRedisTemplate.opsForValue().setIfAbsent(uri, "",
                    Objects.nonNull(repeatSubmitByMethod) ? repeatSubmitByMethod.seconds() : repeatSubmitByClass.seconds(),
                    TimeUnit.SECONDS);
            //如果存在，表示已经请求过了，直接抛出异常，由全局异常进行处理返回指定信息
            if (ifAbsent != null && !ifAbsent) {
                throw new RepeatSubmitException();
            }
            return true;
        }
        return true;
    }
}
