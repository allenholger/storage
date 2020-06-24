package com.example.storage.enumeration;

import java.util.Arrays;
import java.util.List;

/**
  *应用的
  *@author: Allen Holger
 * @date: 2020/6/12 17:11
  */
public enum  ApplicationInitEnum {
    API("仓储服务系统", "storage-api", "http://localhost:8080"),
    ADMIN("仓储管理系统","storage-admin","http://localhost:8081"),
    APP("仓储应用系统", "storage-app", "http://localhost:8082"),
    MINIAPP("仓储小程序", "storage-minapp", "http://localhost:8083");
    private String applicationName;
    private String applicationCode;
    private String applicationUrl;

    private ApplicationInitEnum(String applicationName, String applicationCode, String applicationUrl) {
        this.applicationName = applicationName;
        this.applicationCode = applicationCode;
        this.applicationUrl = applicationUrl;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode;
    }

    public String getApplicationUrl() {
        return applicationUrl;
    }

    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }

    public static List<ApplicationInitEnum> list(){
        return Arrays.asList(API, ADMIN, APP, MINIAPP);
    }
}
