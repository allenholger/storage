package com.example.storage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.storage.entity.OauthClientDetails;
import org.springframework.stereotype.Repository;

/**
  *oauth_client_details 的mapper接口
  *@author: Allen Holger
 * @date: 2020/6/30 15:36
  */
@Repository
public interface OauthClientDetailsMapper extends BaseMapper<OauthClientDetails> {
}
