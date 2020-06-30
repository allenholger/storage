package com.example.storage.initialize;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.storage.constant.DataValidation;
import com.example.storage.entity.Application;
import com.example.storage.entity.OauthClientDetails;
import com.example.storage.enumeration.ApplicationInitEnum;
import com.example.storage.enumeration.GrantTypeEnum;
import com.example.storage.enumeration.ScopeEnum;
import com.example.storage.service.ApplicationService;
import com.example.storage.service.OauthClientDetailsService;
import com.example.storage.util.KeySecretUtils;
import com.example.storage.util.SnowFlakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 本类是应用的初始化类
 * 主要用于初始化应用的，而在本处，主要是用来初始化本应用的
 */
@Component
@Slf4j
class ApplicationInitRunner implements ApplicationRunner {
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;
    @Override
    public void run(ApplicationArguments args) {
        log.info("应用进行初始化，其主要任务就是将本应用写入数据库中");
        List<ApplicationInitEnum> applicationInitEnumList = ApplicationInitEnum.list();
        log.info("应用初始化枚举类列表为：" + applicationInitEnumList.toString());
        if(!applicationInitEnumList.isEmpty()){
            applicationInitEnumList.forEach(applicationInitEnum -> {
                //根据应用名查询数据
                Application application = applicationService.getOne(new QueryWrapper<Application>()
                        .eq("application_code", applicationInitEnum.getApplicationCode())
                        .eq("validation", DataValidation.YES));
                if(application == null){
                    application = new Application();
                    application.setApplicationName(applicationInitEnum.getApplicationName());
                    application.setApplicationCode(applicationInitEnum.getApplicationCode());
                    SnowFlakeUtils snowFlakeUtils = new SnowFlakeUtils();
                    application.setClientId(String.valueOf(snowFlakeUtils.nextId()));
                    application.setAppKey(KeySecretUtils.generateAppKey());
                    application.setAppSecret(KeySecretUtils.generateAppSecret(application.getAppKey(), application.getClientId()));
                    application.setApplicationUrl(applicationInitEnum.getApplicationUrl());
                    if(applicationService.save(application)){
                        log.info(applicationInitEnum.getApplicationName() + "保存成功！");
                    }
                }else{
                    log.info(applicationInitEnum.getApplicationName() + "已经存在！");
                }
            });
        }
        log.info("应用初始化完成");

        //初始化oauth_client_details表的数据
        log.info("初始化oauth_client_details");
        log.info("在本处只用于初始化仓储系统API");
        Application application = applicationService.getOne(new QueryWrapper<Application>().eq("application_code", ApplicationInitEnum.API.getApplicationCode())
                .eq("validation", DataValidation.YES));
        QueryWrapper<OauthClientDetails> queryWrapper = new QueryWrapper<OauthClientDetails>().eq("client_id", application.getClientId()).eq("validation", DataValidation.YES);
        OauthClientDetails oauthClientDetails = oauthClientDetailsService.getOne(queryWrapper);
        if(oauthClientDetails == null){
            oauthClientDetails = new OauthClientDetails();
            oauthClientDetails.setClientId(application.getClientId());
            oauthClientDetails.setClientSecret(application.getAppSecret());
            oauthClientDetails.setAuthorizedGrantTypes(GrantTypeEnum.formatList(GrantTypeEnum.all()));
            oauthClientDetails.setScope(ScopeEnum.ALL.getScope());
            if(oauthClientDetailsService.save(oauthClientDetails)){
                log.info("oauthClientDetailsService保存成功, client_id: " + application.getClientId());
            }
        }else{
            log.info("oauthClientDetails已经存在，无需初始化");
        }
        log.info("oauthClientDetails 初始化完成");
    }
}
