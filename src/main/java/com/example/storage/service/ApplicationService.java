package com.example.storage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.storage.entity.Application;
import com.example.storage.exception.MsgException;
import com.example.storage.model.query.ApplicationPageQuery;
import com.example.storage.model.request.ApplicationRequest;
import com.example.storage.model.vo.ApplicationVO;


/**
  * 应用的service接口
  *@author: Allen Holger
 * @date: 2020/6/10 17:00
  */
public interface ApplicationService extends IService<Application> {
    /**
     * 新增应用
     * @param request
     */
    void add(ApplicationRequest request) throws MsgException;

    /**
     * 应用分页
     * @param query
     * @return
     */
    IPage<ApplicationVO> getPage(ApplicationPageQuery query);
}
