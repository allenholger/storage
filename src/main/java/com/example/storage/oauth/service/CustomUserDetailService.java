package com.example.storage.oauth.service;

import com.example.storage.entity.Account;
import com.example.storage.oauth.details.CustomUserDetails;
import com.example.storage.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * 自定义的userDetail服务，用来封装token的
 * @author Allen Holger
 */
@Component
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //根据账户名去查询账户
        Account account = accountService.findByAccountName(s);
        if(account == null){
            log.info("账户名不存在！");
            throw new UsernameNotFoundException("账户名不存在！ " + s);
        }else{
            return new CustomUserDetails(account.getAccountName(), account.getAccountPassword(), account.getSalt(), Collections.EMPTY_SET);
        }
    }
}
