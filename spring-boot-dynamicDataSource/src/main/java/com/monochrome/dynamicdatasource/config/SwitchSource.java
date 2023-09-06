package com.monochrome.dynamicdatasource.config;

import java.lang.annotation.*;

/**
 * 切换数据源的注解
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface SwitchSource {

      /**
       * 默认切换的数据源KEY
       */
      String DEFAULT_NAME = "hisDataSource";

      /**
       * 需要切换到数据的KEY
       * @return DEFAULT_NAME
       */
      String value() default DEFAULT_NAME;
}