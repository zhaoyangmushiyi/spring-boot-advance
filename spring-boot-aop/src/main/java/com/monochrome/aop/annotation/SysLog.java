package com.monochrome.aop.annotation;

import java.lang.annotation.*;

/**
 * @author monochrome
 * @date 2023/9/15
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
