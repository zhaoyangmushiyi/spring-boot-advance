package com.monochrome.validation.entity.nested;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * @author monochrome
 * @date 2023/8/29
 */
@Data
public class ArticleDTO {

    @NotNull(message = "文章id不能为空")
    @Min(value = 1, message = "文章ID不能为负数")
    private Integer id;

    @NotBlank(message = "文章内容不能为空")
    private String content;

    @NotBlank(message = "作者Id不能为空")
    private String authorId;

    @Future(message = "提交时间不能为过去时间")
    private Date submitTime;

    /**
     * @Valid 这个注解指定CategoryDTO中的属性也需要校验
     */
    @Valid
    @NotNull(message = "分类不能为空")
    private CategoryDTO categoryDTO;

    /**
     * @Valid 这个注解标注在集合上，将会针对集合中每个元素进行校验
     */
    @Valid
    @Size(min = 1, message = "至少一个分类")
    @NotNull(message = "分类不能为空")
    private List<CategoryDTO> categoryDTOS;
}
