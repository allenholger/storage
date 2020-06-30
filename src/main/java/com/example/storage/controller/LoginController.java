package com.example.storage.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.storage.annotation.SysLog;
import com.example.storage.constant.DataValidation;
import com.example.storage.entity.Application;
import com.example.storage.exception.MsgException;
import com.example.storage.model.request.LoginRequest;
import com.example.storage.oauth.constant.LoginType;
import com.example.storage.service.ApplicationService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
@Slf4j
public class LoginController {
    @Value("${spring.application.name}")
    private String appCode;
    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 登录
     * @return
     */
    @SysLog(name = "登录接口")
    @ApiOperation(value = "登录接口")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody LoginRequest loginRequest) throws MsgException {
        if(loginRequest == null){
            throw new MsgException("登录请求体为空");
        }
        if(loginRequest.getLoginType().equalsIgnoreCase(LoginType.PASSWORD)) {
            Application application = applicationService.getOne(new QueryWrapper<Application>().eq("application_code", appCode).eq("validation", DataValidation.YES));
            if (application == null) {
                throw new MsgException("无效的应用名: " + appCode);
            }
            //return String.format("redirect:%s/oauth/token?grant_type=%s&username=%s&password=%s&client_id=%s&client_secret=%s", application.getApplicationUrl(),
            //        "password",loginRequest.getAccountName(), loginRequest.getAccountPassword(), application.getClientId(), application.getAppSecret());
            String url = String.format("%s/oauth/token", application.getApplicationUrl());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            MultiValueMap<String, String> param = new LinkedMultiValueMap();
            param.add("grant_type", "password");
            param.add("username", loginRequest.getAccountName());
            param.add("password", loginRequest.getAccountPassword());
            param.add("client_id", application.getClientId());
            param.add("client_secret", application.getAppSecret());
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers, param);
            ResponseEntity<JSONObject> response = restTemplate.exchange(url, HttpMethod.POST, entity, JSONObject.class);
            return null;
        }else if (loginRequest.getLoginType().equalsIgnoreCase(LoginType.SMS_CODE)){
            Application application = applicationService.getOne(new QueryWrapper<Application>().eq("application_code", appCode).eq("validation", DataValidation.YES));
            if(application == null){
                throw new MsgException("无效的应用名：" + appCode);
            }
            return String.format("redirect:%s/oauth/token?grant_type=%s&username=%s&password=%s", application.getApplicationUrl(),
                    "password",loginRequest.getAccountName(), loginRequest.getAccountPassword());
        }else{
            throw new MsgException("未知的登陆类型： " + loginRequest.getLoginType());
        }
    }
}
