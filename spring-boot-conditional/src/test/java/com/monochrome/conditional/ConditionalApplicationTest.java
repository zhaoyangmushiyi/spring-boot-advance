package com.monochrome.conditional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author monochrome
 * @date 2023/1/19
 */
@SpringBootTest
class ConditionalApplicationTest {

    @Autowired(required = false)
    @Qualifier("win")
    private Object winObject;

    @Autowired(required = false)
    @Qualifier("mac")
    private Object macObject;

    @Test
    public void contextLoads() {
        System.out.println("win:" + winObject);
        System.out.println("mac:" + macObject);
    }

}