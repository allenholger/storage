package com.example.storage.oauth.provider;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 自定义的账户密码详情支持类
 */
@Component
public class CustomUerDetailsProvider extends AbstractUserDetailsAuthenticationProvider {

    /**
     * 校验密码正确性
     * @param userDetails
     * @param usernamePasswordAuthenticationToken
     * @throws AuthenticationException
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        if(usernamePasswordAuthenticationToken.getCredentials() == null){

        }

    }

    /**
     * 获取用户详情
     * @param s
     * @param usernamePasswordAuthenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        return null;
    }
}
