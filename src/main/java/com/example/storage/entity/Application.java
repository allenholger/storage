package com.example.storage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
  * 本类是应用的实体类
  *@author: Allen
  *@since: 2020/6/10
  */
@Data
@TableName("t_application")
public class Application extends BaseEntity<Application>{
    /**
     * 应用的名字
     */
    private String applicationName;

    /**
     * 应用的标识码
     */
    private String applicationCode;

    private String appKey;

    private String appSecret;

    private String clientId;

    /**
     * 应用部署的服务网址
     */
    private String applicationUrl;
}
