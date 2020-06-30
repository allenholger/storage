package com.example.storage.controller;

import com.example.storage.annotation.SysLog;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
  *oauth_client_details的前端控制器
  *@author: Allen Holger
 * @date: 2020/6/30 15:49
  */
@RestController
@RequestMapping("/sys/oauthClientDetails")
@Api("oauth_client_details的前端控制器")
@SysLog(name = "oauth_client_details的前端控制器")
public class OauthClientDetailsController {

}
