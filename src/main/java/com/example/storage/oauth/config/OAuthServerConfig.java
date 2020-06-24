package com.example.storage.oauth.config;

import com.example.storage.oauth.service.CustomUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
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
    //@Autowired
    //private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailService userDetailService;



    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        //endpoints.tokenStore(jdbcTokenStore).authenticationManager(authenticationManager).userDetailsService(userDetailService);
        endpoints.tokenServices(defaultTokenServices());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).clients(clientDetails());
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
        //tokenServices.setClientDetailsService(clientDetails());
        // token有效期自定义设置，默认12小时
        tokenServices.setAccessTokenValiditySeconds(60*60*12);
        // refresh_token默认30天
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);
        return tokenServices;
    }

    @Bean
    public ClientDetailsService clientDetails(){
        return new JdbcClientDetailsService(dataSource);
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.allowFormAuthenticationForClients();
    }

}
