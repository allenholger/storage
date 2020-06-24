package com.example.storage.oauth.service;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

/**
 * 自定义的客户端详情服务
 * @author Allen Holger
 */
public class CustomClientDetailService implements ClientDetailsService {

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        return null;
    }
}
