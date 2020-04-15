package com.example.storage.controller;

import com.example.storage.exception.MsgException;
import com.example.storage.model.request.CreateAccountRequest;
import com.example.storage.model.response.ApiResponse;
import com.example.storage.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账户的controller类
 * @author allen
 */
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
