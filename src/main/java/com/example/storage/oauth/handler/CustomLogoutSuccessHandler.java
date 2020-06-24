package com.example.storage.oauth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 自定义登出成功的处理器, 登出成功时，应该清除掉token信息等
 * @author Allen Holger
 */
@Component
@Slf4j
//@ConfigurationProperties(prefix = "spring.datasource")
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    private static final String BEARER_AUTHENTICATION = "Bearer ";
    private static final String HEADER_AUTHORIZATION = "authorization";

    @Autowired
    private JdbcTokenStore jdbcTokenStore;
    /*
    @Autowired
    private DataSource dataSource;

    @Bean(name = "jdbcTokenStore")
    public JdbcTokenStore jdbcTokenStore(){
       return new JdbcTokenStore(dataSource);
    }
*/
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info("应该清除掉token信息");
        String authorization = httpServletRequest.getHeader(HEADER_AUTHORIZATION);
        if(authorization != null && authorization.startsWith(BEARER_AUTHENTICATION)){
            log.info("应该将authentication中的token信息清除掉。");
            OAuth2AccessToken oAuth2AccessToken = jdbcTokenStore.readAccessToken(authorization.split(" ")[0]);
            if(oAuth2AccessToken != null){
                log.info("oAuth2AccessToken 为：" + oAuth2AccessToken.getValue());
                jdbcTokenStore.removeAccessToken(oAuth2AccessToken);
             }
        }
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    }
}
