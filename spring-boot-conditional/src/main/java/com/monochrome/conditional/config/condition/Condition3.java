package com.monochrome.conditional.config.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.type.AnnotatedTypeMetadata;

class Condition3 implements Condition, PriorityOrdered {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        System.out.println(this.getClass().getName());
        return true;
    }

    @Override
    public int getOrder() {
        return 1000;
    }
}
