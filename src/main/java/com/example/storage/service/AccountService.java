package com.example.storage.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.storage.entity.Account;
import com.example.storage.exception.MsgException;
import com.example.storage.model.request.CreateAccountRequest;

/**
 * 账户的service接口
 * @author allen
 */
public interface AccountService extends IService<Account> {

    /**
     * 创建账户
     * @param request
     */
    void createAccount(CreateAccountRequest request) throws MsgException;
}
