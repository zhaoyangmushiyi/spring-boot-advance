package com.monochrome.conditional.config;

import com.monochrome.conditional.config.condition.MacCondition;
import com.monochrome.conditional.config.condition.WindowsCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author monochrome
 * @date 2023/1/19
 */
@Configuration
public class CustomConfig {

    @Bean("win")
    @Conditional(value = {WindowsCondition.class})
    public Object win() {
        return new Object();
    }

    @Bean("mac")
    @Conditional(value = {MacCondition.class})
    public Object mac() {
        return new Object();
    }
}
