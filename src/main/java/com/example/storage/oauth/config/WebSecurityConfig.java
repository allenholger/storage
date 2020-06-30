package com.example.storage.oauth.config;

import com.example.storage.oauth.provider.CustomUerDetailsProvider;
import com.example.storage.oauth.service.CustomUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
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
@Order(-1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailService userDetailService;
    //@Autowired
    //private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUerDetailsProvider customUerDetailsProvider;

    //@Autowired
    //private CustomAuthenticationFilter customAuthenticationFilter;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index", "/swagger-ui.html/**", "/login", "/logout");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().and().httpBasic().disable()
                .formLogin().and().authorizeRequests().anyRequest().authenticated();
        //http.addFilter(customAuthenticationFilter);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
        auth.authenticationProvider(customUerDetailsProvider);
        //auth.passwordEncoder(passwordEncoder);
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        //String username = "";
//        //UserDetails userDetails = userDetailService.loadUserByUsername(username);
//        //return new Md5PasswordEncoder(((CustomUserDetails)userDetails).getSalt());
//        return new Md5PasswordEncoder();
//    }

//    @Bean
//    @Primary
//    public CustomAuthenticationFilter customAuthenticationFilter(){
//        return new CustomAuthenticationFilter();
//    }
}
