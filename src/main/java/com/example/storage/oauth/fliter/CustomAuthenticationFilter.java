package com.example.storage.oauth.fliter;

import com.example.storage.oauth.constant.LoginType;
//import com.example.storage.oauth.token.SmsDetailToken;
//import com.example.storage.oauth.token.UserDetailToken;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

///**
//  *自定义的认证拦截器
//  *@author: Allen Holger
//  *@since: 2020/6/1
//  */
//public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
//
//    public CustomAuthenticationFilter(){
//        super(new AntPathRequestMatcher("/security"));
//
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
//        if(!httpServletRequest.getMethod().equalsIgnoreCase(RequestMethod.POST.name())){
//            throw new AuthenticationServiceException("Authentication method : " + httpServletRequest.getMethod() + "is not support");
//        }
//        AbstractAuthenticationToken token = null;
//        String loginType = obtainParam(httpServletRequest, "loginType");
//        if(loginType.equalsIgnoreCase(LoginType.PASSWORD)){
//            token = new UserDetailToken(httpServletRequest.getParameter("username"), httpServletRequest.getParameter("password"));
//        }else if(loginType.equalsIgnoreCase(LoginType.SMS_CODE)){
//            token = new SmsDetailToken(httpServletRequest.getParameter("username"), httpServletRequest.getParameter("smsCode"));
//        }else{
//            throw new AuthenticationServiceException("Unknown login type :" + httpServletRequest.getParameter("loginType"));
//        }
//        httpServletResponse.setHeader("Content-Type", "application/json");
//        token.setDetails(authenticationDetailsSource.buildDetails(httpServletRequest));
//        return this.getAuthenticationManager().authenticate(token);
//    }
//
//    /**
//     * 从httpServletRequest 获取参数
//     * @param httpServletRequest
//     * @param key
//     * @return
//     */
//    private String obtainParam(HttpServletRequest httpServletRequest, String key){
//        String value = httpServletRequest.getParameter(key);
//        return value;
//    }
//}
