package com.monochrome.validation.entity.group;

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
    /**
     * 文章ID只在修改的时候需要检验，因此指定groups为修改的分组
     */
    @NotNull(message = "文章id不能为空", groups = UpdateArticleDTO.class)
    @Min(value = 1, message = "文章ID不能为负数", groups = UpdateArticleDTO.class)
    private Integer id;

    /**
     * 文章内容添加和修改都是必须校验的，groups需要指定两个分组
     */
    @NotBlank(message = "文章内容不能为空", groups = {AddArticleDTO.class, UpdateArticleDTO.class})
    private String content;

    /**
     * 添加作者时作者ID不能为空因此指定groups为添加的分组
     */
    @NotBlank(message = "作者Id不能为空", groups = AddArticleDTO.class)
    private String authorId;

    /**
     * 提交时间是添加和修改都需要校验的，因此指定groups两个
     */
    @Future(message = "提交时间不能为过去时间", groups = {AddArticleDTO.class, UpdateArticleDTO.class})
    private Date submitTime;

    //修改文章的分组
    public interface UpdateArticleDTO {
    }

    //添加文章的分组
    public interface AddArticleDTO {
    }

}
