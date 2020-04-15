package com.example.storage.model.response;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;

/**
 * 自定义响应类
 * @author Allen
 */
public class ApiResponse extends HashMap<String, Object> {
    private ApiResponse(){}

    /**
     * 失败的响应
     * @return
     */
    public static ApiResponse fail(){
        return fail("");
    }

    /**
     * 失败的响应
     * @param msg         失败的消息
     * @return
     */
    public static ApiResponse fail(String msg){
        ApiResponse response = new ApiResponse();
        response.put("success", false);
        response.put("msg", msg);
        return response;
    }

    /**
     * 失败的响应
     * @param msg      失败的消息
     * @param code     错误码
     * @return
     */
    public static ApiResponse fail(String msg, Integer code){
        ApiResponse response = new ApiResponse();
        response.put("success", false);
        response.put("msg", msg);
        response.put("code", code);
        return response;
    }

    /**
     * 成功的响应
     * @return
     */
    public static ApiResponse success(){
        return success(null);
    }

    /**
     * 成功的响应
     * @param data    返回的数据对象
     * @return
     */
    public static ApiResponse success(Object data){
        ApiResponse response = new ApiResponse();
        response.put("success", true);
        response.put("data", data);
        return response;
    }

    /**
     * 消息的响应
     * @param msg   消息
     * @return
     */
    public static ApiResponse msg(String msg){
        ApiResponse response = new ApiResponse();
        response.put("msg", msg);
        return response;
    }

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
