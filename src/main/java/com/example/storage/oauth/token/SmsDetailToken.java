package com.example.storage.oauth.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;

import java.util.Collection;

/**
  * 短信验证码登录的token
  *@author: Allen
  *@since: 2020/6/9
  */
public class SmsDetailToken extends AbstractAuthenticationToken {
    private String telephone;
    private String smsCode;


    @Override
    public Object getCredentials() {
        return smsCode;
    }

    @Override
    public Object getPrincipal() {
        return telephone;
    }

    public SmsDetailToken(Collection<? extends GrantedAuthority> authorities, String telephone, String smsCode) {
        super(authorities);
        this.telephone = telephone;
        this.smsCode = smsCode;
        super.setAuthenticated(true);
    }

    public SmsDetailToken(String telephone, String smsCode) {
        super(null);
        this.telephone = telephone;
        this.smsCode = smsCode;
        super.setAuthenticated(false);
    }

}
