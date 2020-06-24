package com.example.storage.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.storage.annotation.SysLog;
import com.example.storage.constant.DataValidation;
import com.example.storage.entity.Application;
import com.example.storage.exception.MsgException;
import com.example.storage.mapper.ApplicationMapper;
import com.example.storage.model.query.ApplicationPageQuery;
import com.example.storage.model.request.ApplicationRequest;
import com.example.storage.model.vo.ApplicationVO;
import com.example.storage.service.ApplicationService;
import com.example.storage.util.KeySecretUtils;
import com.example.storage.util.SnowFlakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
  *应用的service接口
  *@author: Allen Holger
 * @date: 2020/6/10 17:03
  */
@Service
@SysLog(name = "应用的service接口类")
@Slf4j
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements ApplicationService {
    @Autowired
    private ApplicationMapper applicationMapper;

    @Override
    @SysLog(name = "新增应用的add方法")
    @Transactional(rollbackFor = Exception.class)
    public void add(ApplicationRequest request) throws MsgException {
        Application application = applicationMapper.selectOne(new LambdaQueryWrapper<Application>().eq(Application::getApplicationCode, request.getApplicationCode())
                .eq(Application::getValidation, DataValidation.YES));
        if(application != null){
            throw new MsgException(String.format("应用已存在！【applicationName: %s】【applicationCode: %s】【applicationUrl：%s】", application.getApplicationName(),
                    application.getApplicationCode(), application.getApplicationUrl()));
        }
        application = new Application();
        BeanUtils.copyProperties(request, application);
        SnowFlakeUtils snowFlakeUtils = new SnowFlakeUtils();
        application.setClientId(String.valueOf(snowFlakeUtils.nextId()));
        application.setAppKey(KeySecretUtils.generateAppKey());
        application.setAppSecret(KeySecretUtils.generateAppSecret(application.getAppKey(), application.getClientId()));
        log.info(application.toString());
        if(applicationMapper.insert(application) !=  1){
            throw new MsgException("应用信息新增失败");
        }
    }

    @Override
    @SysLog(name = "应用分页查询")
    public IPage<ApplicationVO> getPage(ApplicationPageQuery query) {
        IPage<ApplicationVO> page = new Page<>(query.getPageIndex(), query.getPageSize());
        List<Application> applicationList = applicationMapper.selectList(new LambdaQueryWrapper<Application>().eq(Application::getValidation, DataValidation.YES));
        if(applicationList != null && !applicationList.isEmpty()){
            List<ApplicationVO> applicationVOList = applicationList.stream().map(application -> {
                ApplicationVO applicationVO = new ApplicationVO();
                BeanUtils.copyProperties(application, applicationVO);
                return applicationVO;
            }).collect(Collectors.toList());
            page.setRecords(applicationVOList);
        }else{
            page.setRecords(null);
        }
        return page;
    }


}
