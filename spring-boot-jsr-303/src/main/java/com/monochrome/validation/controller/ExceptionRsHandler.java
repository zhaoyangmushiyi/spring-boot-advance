package com.monochrome.validation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.monochrome.validation.mapper.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionRsHandler {
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 参数校验异常步骤
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    public String onException(Exception e) throws JsonProcessingException {
        BindingResult bindingResult = null;
        if (e instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
        } else if (e instanceof BindException) {
            bindingResult = ((BindException) e).getBindingResult();
        }
        Map<String, String> errorMap = new HashMap<>(16);
        bindingResult.getFieldErrors().forEach((fieldError) ->
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage())
        );
        return objectMapper.writeValueAsString(errorMap);
    }
}