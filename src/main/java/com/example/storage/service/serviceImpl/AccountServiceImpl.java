package com.example.storage.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.storage.entity.Account;
import com.example.storage.exception.MsgException;
import com.example.storage.mapper.AccountMapper;
import com.example.storage.model.request.CreateAccountRequest;
import com.example.storage.service.AccountService;
import com.example.storage.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 账户service接口的实现类
 * @author allen
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    /**
     * 创建账户，账户密码储存的均为加密后的密码
     * @param request
     */
    @Override
    public void createAccount(CreateAccountRequest request) throws MsgException {
        Account account = new Account();
        account.setAccountName(request.getAccountName());
        String salt = PasswordUtils.generateSalt();
        //账户密码存储的是经过加密后的密码
        account.setAccountPassword(PasswordUtils.encrypt(request.getAccountPassword(), salt));
        account.setSalt(salt);
        int result =accountMapper.insert(account);
        if(result != 1){
            throw new MsgException("创建账户失败！");
        }
    }
}
