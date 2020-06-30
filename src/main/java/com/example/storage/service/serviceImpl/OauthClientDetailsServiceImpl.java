package com.example.storage.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.storage.entity.OauthClientDetails;
import com.example.storage.mapper.OauthClientDetailsMapper;
import com.example.storage.service.OauthClientDetailsService;
import org.springframework.stereotype.Service;

/**
  *oauth_client_details的service接口实现类
  *@author: Allen Holger
 * @date: 2020/6/30 15:44
  */
@Service
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails>
        implements OauthClientDetailsService {

}
