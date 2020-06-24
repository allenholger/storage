package com.example.storage.oauth.token.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * JDBC的方式存储accessToken
 * @author Allen Holger
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class JdbcTokenStoreConfig {
    @Autowired
    private DataSource dataSource;

    @Bean(name = "jdbcTokenStore")
    public JdbcTokenStore jdbcTokenStore(){
        return new JdbcTokenStore(dataSource);
    }
}
