package com.example.storage.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
  *新增应用的请求
  *@author: Allen Holger
  *@since: 2020/6/4
  */
@Data
public class ApplicationRequest {
    private Long id;
    /**
     * 应用的名字
     */
    @NotNull(message = "应用名不能为空")
    private String applicationName;

    /**
     * 应用的标识码
     */
    @NotNull(message = "应用标识码不能为空")
    private String applicationCode;

    /**
     * 应用部署的网址
     */
    @NotNull(message = "应用部署的url不能为空")
    private String applicationUrl;
}
