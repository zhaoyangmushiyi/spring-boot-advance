package com.monochrome.validation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.monochrome.validation.entity.group.ArticleDTO;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author monochrome
 * @date 2023/8/29
 */
@RestController
@RequestMapping("/groupArticle")
public class GroupArticleController {


    @PostMapping("/add")
    public String add(@Validated(value = ArticleDTO.AddArticleDTO.class) @RequestBody ArticleDTO articleDTO,
                      BindingResult bindingResult) throws JsonProcessingException {
        //如果有错误提示信息
        if (bindingResult.hasErrors()) {
            Map<String, String> map = new HashMap<>();
            bindingResult.getFieldErrors().forEach((item) -> {
                String message = item.getDefaultMessage();
                String field = item.getField();
                map.put(field, message);
            });
            //返回提示信息
            //return objectMapper.writeValueAsString(map);
        }
        return "success";
    }

    @PostMapping("/update")
    public String update(@Validated(value = ArticleDTO.UpdateArticleDTO.class) @RequestBody ArticleDTO articleDTO,
                      BindingResult bindingResult) throws JsonProcessingException {
        //如果有错误提示信息
        if (bindingResult.hasErrors()) {
            Map<String, String> map = new HashMap<>();
            bindingResult.getFieldErrors().forEach((item) -> {
                String message = item.getDefaultMessage();
                String field = item.getField();
                map.put(field, message);
            });
            //返回提示信息
            //return objectMapper.writeValueAsString(map);
        }
        return "success";
    }
}
