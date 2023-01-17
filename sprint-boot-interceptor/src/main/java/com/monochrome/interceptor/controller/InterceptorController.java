package com.monochrome.interceptor.controller;

import com.monochrome.interceptor.config.RepeatSubmit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author monochrome
 * @date 2023/1/17
 */
@RestController
@RequestMapping("interceptor")
public class InterceptorController {

    @GetMapping("hello")
    @RepeatSubmit
    public String hello() {
        return "Hello";
    }

}
