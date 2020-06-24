package com.example.storage.oauth.config;

import com.example.storage.oauth.service.CustomUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 配置Spring Security
 * ResourceServerConfig 是比SecurityConfig 的优先级低的
 * @author Allen Holger
 */
@Configuration
@Slf4j
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailService userDetailService;


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index", "/swagger-ui.html/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers("/oauth/**")
                .and().authorizeRequests().antMatchers("/oauth/**").authenticated()
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }
}
