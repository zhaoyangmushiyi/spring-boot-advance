package com.monochrome.validation.entity.simple;

import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

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
}
