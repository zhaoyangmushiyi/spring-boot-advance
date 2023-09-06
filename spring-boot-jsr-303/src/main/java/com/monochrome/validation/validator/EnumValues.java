package com.monochrome.validation.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {EnumValuesConstraintValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@NotNull(message = "不能为空")
public @interface EnumValues {
    /**
     * 提示消息
     */
    String message() default "传入的值不在范围内";

    /**
     * 分组
     *
     * @return
     */
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 可以传入的值
     * @return
     */
    int[] values() default {};
}