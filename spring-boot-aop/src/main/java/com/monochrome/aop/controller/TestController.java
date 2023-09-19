package com.monochrome.aop.controller;

import com.monochrome.aop.annotation.SysLog;
import com.monochrome.aop.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author monochrome
 * @date 2023/9/15
 */
@RestController
public class TestController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/test")
    @SysLog
    public String test() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Success";
    }

    @GetMapping("/add")
    public String add() {
        try {
            articleService.b();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Success";
    }
}
