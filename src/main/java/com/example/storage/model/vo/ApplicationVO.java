package com.example.storage.model.vo;

import lombok.Data;

/**
  * 应用的VO
  *@author: Allen Holger
 * @date: 2020/6/11 23:14
  */
@Data
public class ApplicationVO {

    private Long id;

    private String applicationName;

    private String applicationCode;

    private String applicationUrl;
}
