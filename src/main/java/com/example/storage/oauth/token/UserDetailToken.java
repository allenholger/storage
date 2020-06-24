package com.example.storage.oauth.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;

import java.util.Collection;

/**
  * 通过自定义的用户详情token 用来实现用户通过手机号和密码登陆后实现的功能
  *@author: Allen Holger
  *@since: 2020/6/8
  */
public class UserDetailToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 1L;
    private String mobile;
    private String password;

    public UserDetailToken(Collection<? extends GrantedAuthority> authorities, String mobile, String password) {
        super(authorities);
        this.mobile = mobile;
        this.password = password;
        super.setAuthenticated(true);
    }

    public UserDetailToken(String mobile, String password) {
        super(null);
        this.mobile = mobile;
        this.password = password;
        super.setAuthenticated(false);
    }

    public UserDetailToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        return this.password;
    }

    @Override
    public Object getPrincipal() {
        return this.mobile;
    }

}
