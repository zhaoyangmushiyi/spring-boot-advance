package com.monochrome.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * @author monochrome
 * @date 2023/9/5
 */
public class EnumValuesConstraintValidator implements ConstraintValidator<EnumValues, Integer> {

    /**
     * 存储枚举的值
     */
    private Set<Integer> ints = new HashSet<>();

    @Override
    public void initialize(EnumValues enumValues) {
        for (int value : enumValues.values()) {
            ints.add(value);
        }
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        //判断是否包含这个值
        return ints.contains(value);
    }
}
