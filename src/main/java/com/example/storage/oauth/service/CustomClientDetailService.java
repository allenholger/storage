package com.example.storage.oauth.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.storage.constant.DataValidation;
import com.example.storage.entity.Application;
import com.example.storage.entity.OauthClientDetails;
//import com.example.storage.exception.MsgException;
//import com.example.storage.oauth.details.CustomClientDetails;
//import com.example.storage.service.ApplicationService;
//import com.example.storage.service.OauthClientDetailsService;
//import com.google.common.collect.Sets;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.ApplicationContext;
//import org.springframework.security.oauth2.provider.ClientDetails;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.security.oauth2.provider.ClientRegistrationException;
//import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
//import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * 自定义的客户端详情服务
 * @author Allen Holger
 */
//@Component
//
//public class CustomClientDetailService implements ClientDetailsService {
//    @Autowired
//    private ApplicationService applicationService;
//    @Autowired
//    private OauthClientDetailsService oauthClientDetailsService;
//
//    @Override
//    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
//        Application application = applicationService.getOne(new QueryWrapper<Application>().eq("client_id", Long.valueOf(s)).eq("validation", DataValidation.YES));
//        if(application == null){
//            throw new ClientRegistrationException("application is not eixsts, which client id is " + s + "!");
//        }
//        OauthClientDetails oauthClientDetails = oauthClientDetailsService.getOne(new QueryWrapper<OauthClientDetails>().eq("client_id", application.getClientId()).eq("validation", DataValidation.YES));
//        if(oauthClientDetails == null){
//            throw new ClientRegistrationException("oauthClientDetails is not exists.");
//        }
//        return new CustomClientDetails(String.valueOf(application.getClientId()), Sets.newHashSet(oauthClientDetails.getResourceIds()), false,
//                oauthClientDetails.getClientSecret(), false, Sets.newHashSet(oauthClientDetails.getScope()), Sets.newHashSet(oauthClientDetails.getAuthorizedGrantTypes()));
//    }
//}
