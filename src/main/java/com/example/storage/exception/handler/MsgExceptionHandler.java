package com.example.storage.exception.handler;

import com.example.storage.exception.MsgException;
import com.example.storage.model.response.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 消息异常的全局处理类
 * @author allen
 */
@RestControllerAdvice
public class MsgExceptionHandler{
    @ExceptionHandler(value = MsgException.class)
    public ApiResponse msgExceptionHandler(Exception e){
       return ApiResponse.fail(e.getMessage());
    }
}
