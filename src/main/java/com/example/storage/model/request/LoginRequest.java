package com.example.storage.model.request;

import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 登录的请求类
 * @author Allen Holger
 */
@Data
public class LoginRequest {
    //登录的方式，目前登录方式有两种，一种是密码登录，另外一种是短信验证码登录
    private String loginType;
    @NotNull(message = "账户名不能为空")
    @NotEmpty(message = "账户名不能为空")
    private String accountName;
    @NotNull(message = "账户名不能为空")
    @NotEmpty(message = "账户密码不能为空")
    private String accountPassword;
}
