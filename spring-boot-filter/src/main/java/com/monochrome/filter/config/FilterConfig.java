package com.monochrome.filter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @author monochrome
 * @date 2023/1/17
 */
@Configuration
public class FilterConfig {

    @Autowired
    CrosFilter crosFilter;

    @Bean
    FilterRegistrationBean<CrosFilter> crosFilter() {
        FilterRegistrationBean<CrosFilter> registrationBean = new FilterRegistrationBean<CrosFilter>();
        registrationBean.setFilter(crosFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("crosFilter");
        // 设置优先级别
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }

}
