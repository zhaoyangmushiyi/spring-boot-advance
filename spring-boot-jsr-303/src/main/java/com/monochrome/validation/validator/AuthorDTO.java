package com.monochrome.validation.validator;

import lombok.Data;

@Data
public class AuthorDTO {
    @EnumValues(values = {1, 2}, message = "性别只能传入1或者2")
    private Integer gender;
}