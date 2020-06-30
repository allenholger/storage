package com.example.storage.oauth.config;

import com.example.storage.oauth.exception.CustomAuthenticationEntryPoint;
import com.example.storage.oauth.handler.CustomLoginFailureHandler;
import com.example.storage.oauth.handler.CustomLoginSuccessHandler;
import com.example.storage.oauth.handler.CustomLogoutSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源服务的配置类
 * @author Allen Holger
 */
@Configuration
@EnableResourceServer
@Slf4j
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;
    @Autowired
    private CustomLoginFailureHandler customLoginFailureHandler;
    @Autowired
    private CustomLoginSuccessHandler customLoginSuccessHandler;

    //配置URL访问权限
    @Override
    public void configure(HttpSecurity http) throws Exception {
        log.info("配置URL的访问权限");
        http.csrf().disable()
                .formLogin().loginProcessingUrl("/login").successHandler(customLoginSuccessHandler).failureHandler(customLoginFailureHandler).permitAll()
                .and().exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
                .and().logout().logoutUrl("/logout").logoutSuccessHandler(customLogoutSuccessHandler).permitAll()
                .and().authorizeRequests().antMatchers("/oauth/**").permitAll()
                .and().authorizeRequests().antMatchers("/security/**").authenticated()
                .and().httpBasic().disable();
    }

}
