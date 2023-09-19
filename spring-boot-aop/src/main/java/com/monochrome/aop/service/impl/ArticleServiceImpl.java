package com.monochrome.aop.service.impl;

import com.monochrome.aop.annotation.SysLog;
import com.monochrome.aop.service.ArticleService;
import com.monochrome.aop.utils.ApplicationContextUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    /**
     * 注入自身的Bean
     */
    @Autowired
    private ArticleService articleService;

    @SysLog
    @Override
    public void a() {
    }

    @Override
    public void b() {
        articleService.a();
        ArticleService articleService1 = ApplicationContextUtils.getApplicationContext().getBean(ArticleService.class);
        articleService1.a();
        ((ArticleService) AopContext.currentProxy()).a();
    }
}