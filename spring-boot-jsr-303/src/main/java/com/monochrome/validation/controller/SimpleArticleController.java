package com.monochrome.validation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.monochrome.validation.entity.simple.ArticleDTO;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author monochrome
 * @date 2023/8/29
 */
@RestController
@RequestMapping("/simpleArticle")
public class SimpleArticleController {

    /**
     * 添加文章
     *
     * @param articleDTO    文章
     * @param bindingResult
     * @return
     */
    @PostMapping("/add")
    public String add(@Valid @RequestBody ArticleDTO articleDTO, BindingResult bindingResult) throws
            JsonProcessingException {
        // 如果有错误提示信息
        if (bindingResult.hasErrors()) {
            Map<String, String> map = new HashMap<>();
            bindingResult.getFieldErrors().forEach((item) -> {
                String message = item.getDefaultMessage();
                String field = item.getField();
                map.put(field, message);
            });
            // 返回提示信息
            // return objectMapper.writeValueAsString(map);
        }
        return "success111";
    }
}
