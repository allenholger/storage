package com.example.storage.exception.handler;

import com.example.storage.exception.MsgException;
import com.example.storage.model.response.ApiResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 本类是全局异常的处理类
 * @author Allen Holger
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义消息异常处理器
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MsgException.class)
    public ApiResponse msgExceptionHandler(MsgException e){
        return ApiResponse.fail(e.getMessage());
    }

    /**
     * 基于spring valiadtion 参数校验异常处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse MethodArgumentNotValidExceptionHanler(MethodArgumentNotValidException e){
        BindingResult result = e.getBindingResult();
        List<FieldError> errors = result.getFieldErrors();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < errors.size(); i++){
            if(i == errors.size() - 1){
                sb.append(errors.get(i).getDefaultMessage());
            }else{
                sb.append(errors.get(i).getDefaultMessage()).append(",");
            }
        }
        return ApiResponse.fail(sb.toString());
    }
}
