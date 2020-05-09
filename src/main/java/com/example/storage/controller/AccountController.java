package com.example.storage.controller;

import com.example.storage.exception.MsgException;
import com.example.storage.model.request.CreateAccountRequest;
import com.example.storage.model.response.ApiResponse;
import com.example.storage.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账户的controller类
 * @author allen
 */
@Api(value = "账户的前端控制器")
@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * 创建账户
     * @param request
     * @return
     */
    @ApiOperation(value = "创建账户", notes = "根据Account实体来创建账户")
    @ApiImplicitParam(name = "request", value = "创建账户的请求", required = true, dataType = "CreateAccountRequest")
    @PostMapping("/create")
    public ApiResponse createAccount(@RequestBody CreateAccountRequest request) throws MsgException {
        if(request == null){
            throw new MsgException("request 为空");
        }
        if(request.getAccountName() == null || request.getAccountName().equalsIgnoreCase("".trim())){
            throw new MsgException("账户名为空");
        }
        if(request.getAccountPassword() == null || request.getAccountPassword().equalsIgnoreCase("".trim())){
            throw new MsgException("账户密码为空");
        }
        accountService.createAccount(request);
        return ApiResponse.success("创建账户成功");
    }
}
