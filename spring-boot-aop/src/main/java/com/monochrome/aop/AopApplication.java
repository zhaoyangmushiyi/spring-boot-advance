package com.monochrome.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author monochrome
 * @date 2023/9/15
 */
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class AopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }
}
