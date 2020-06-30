package com.example.storage.oauth.config;

import com.example.storage.oauth.service.CustomClientDetailService;
import com.example.storage.oauth.service.CustomUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * 本类是认证服务的配置类
 * @author Allen Holger
 */
@Configuration
@Slf4j
@EnableAuthorizationServer
public class OAuthServerConfig extends AuthorizationServerConfigurerAdapter{

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTokenStore jdbcTokenStore;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private CustomClientDetailService customClientDetailService;
    @Autowired
    private WebResponseExceptionTranslator webResponseExceptionTranslator;
    @Autowired
    private DefaultTokenServices defaultTokenServices;



    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenServices(defaultTokenServices);
        endpoints.authenticationManager(authenticationManager);
        endpoints.userDetailsService(customUserDetailService);
        endpoints.setClientDetailsService(customClientDetailService);
        endpoints.reuseRefreshTokens(false);
        endpoints.exceptionTranslator(webResponseExceptionTranslator);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).clients(customClientDetailService);

    }

    /**
     * 自定义TokenServices的时候，需要设置@Primary，否则报错
     * @return
     */
    @Bean
    @Primary
    public DefaultTokenServices defaultTokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(jdbcTokenStore);
        tokenServices.setSupportRefreshToken(true);
        // token有效期自定义设置，默认2小时
        tokenServices.setAccessTokenValiditySeconds(60*60*2);
        // refresh_token默认30天
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);
        return tokenServices;
    }

    @Bean
    public ClientDetailsService clientDetails(){
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public WebResponseExceptionTranslator webResponseExceptionTranslator(){
        return new DefaultWebResponseExceptionTranslator();
    }

    @Bean
    public JdbcTokenStore jdbcTokenStore(){
        return new JdbcTokenStore(dataSource);
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        //允许表单登录
        security.allowFormAuthenticationForClients();
        //允许check_token
        security.checkTokenAccess("permitAll()");
        security.tokenKeyAccess("permitAll()");
    }

}
