package com.example.storage.oauth2.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.storage.constant.DataValidation;
import com.example.storage.entity.Account;
import com.example.storage.entity.Application;
import com.example.storage.oauth.details.CustomUserDetails;
import com.example.storage.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 自定义用户详情服务
 */
public class CustomUserDetailServie implements UserDetailsService {
    @Autowired
    private AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = accountService.getOne(new QueryWrapper<Account>().eq("account_name", s)
                .eq("validation", DataValidation.YES));
        if(account == null){
            throw new UsernameNotFoundException("账户名不存在！" + s);
        }
        return new CustomUserDetails(account.getAccountName(), account.getAccountPassword(),
                account.getSalt(), null);
    }
}
