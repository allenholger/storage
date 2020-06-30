package com.example.storage.entity;

import lombok.Data;

/**
  *oauth_client_details的实体类
  *@author: Allen Holger
 * @date: 2020/6/30 15:23
  */
@Data
public class OauthClientDetails extends BaseEntity<OauthClientDetails>{
    private String clientId;
    private String clientSecret;
    private String resourceIds;
    private String scope;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private String additionalInformation;
    private String autoapprove;

}
