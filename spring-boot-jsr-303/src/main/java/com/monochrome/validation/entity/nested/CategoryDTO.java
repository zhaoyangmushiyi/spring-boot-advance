package com.monochrome.validation.entity.nested;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 文章分类
 */
@Data
public class CategoryDTO {
    @NotNull(message = "分类ID不能为空")
    @Min(value = 1, message = "分类ID不能为负数")
    private Integer id;
    @NotBlank(message = "分类名称不能为空")
    private String name;
}