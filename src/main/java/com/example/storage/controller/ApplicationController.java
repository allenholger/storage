package com.example.storage.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.storage.annotation.SysLog;
import com.example.storage.constant.DataValidation;
import com.example.storage.entity.Application;
import com.example.storage.exception.MsgException;
import com.example.storage.model.query.ApplicationPageQuery;
import com.example.storage.model.request.ApplicationRequest;
import com.example.storage.model.response.ApiResponse;
import com.example.storage.service.ApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
  *应用的前端控制类
  *@author: Allen Holger
  *@since: 2020/6/4
  */
@Api("应用的前端控制类")
@SysLog(name = "应用的前端控制类")
@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @ApiOperation("新增应用")
    @SysLog(name = "新增应用")
    @PostMapping("/add")
    public ApiResponse add(@RequestBody ApplicationRequest request) throws MsgException {
        if (request == null) {
            throw new MsgException("新增应用的请求体为空");
        }
        //保存应用
        applicationService.add(request);
        return ApiResponse.success("应用新增成功！");
    }

    @ApiOperation("删除应用" )
    @SysLog(name = "删除应用")
    @GetMapping("delete/{id}")
    public ApiResponse delete(@PathVariable("id") Long id) throws MsgException {
        //根据Id查询应用的信息
        Application application = applicationService.getOne(new QueryWrapper<Application>().eq("id", id).eq("validation", DataValidation.YES));
        if(application == null){
            throw new MsgException("应用不存在！【id: " + id + "】");
        }
        application.setValidation(DataValidation.NO);
        if(applicationService.updateById(application)){
            return ApiResponse.success("应用删除成功");
        }else{
            throw new MsgException("应用删除失败");
        }
    }

    @ApiOperation("更新应用")
    @SysLog(name = "更新应用")
    @PostMapping("update")
    public ApiResponse update(@RequestBody ApplicationRequest request) throws MsgException {
        if(request == null){
            throw new MsgException("修改应用的请求体为空");
        }
        //根据Id查询应用
        Application application = applicationService.getOne(new QueryWrapper<Application>().eq("id", request.getId()));
        if(application == null){
            throw new MsgException("应用信息不存在！【id: " + request.getId() + "】");
        }
        if(!application.getApplicationCode().equalsIgnoreCase(request.getApplicationCode())){
            //根据applicationCode查询应用
            Application applicationByCode = applicationService.getOne(new QueryWrapper<Application>().eq("application_code", request.getApplicationCode()).eq("state", 0));
            if(applicationByCode != null){
                throw new MsgException("无效的applicationCode，applicationCode已经存在！【applicationCode：" + request.getApplicationCode() + "】");
            }
        }
        BeanUtils.copyProperties(request, application);
        if(applicationService.updateById(application)){
            return ApiResponse.success("应用信息修改成功");
        }else{
            throw new MsgException("应用信息修改失败");
        }
    }

    @ApiOperation("根据Id查询应用详情")
    @SysLog(name = "根据Id查询应用详情")
    @GetMapping("/find/{id}")
    public ApiResponse find(@PathVariable("id") Long id){
        return ApiResponse.success(applicationService.getOne(new LambdaQueryWrapper<Application>().eq(Application::getId, id).eq(Application::getValidation, DataValidation.YES)));
    }

    @ApiOperation("分页查询")
    @SysLog(name = "应用分页查询")
    @GetMapping("page")
    public ApiResponse page(ApplicationPageQuery query) throws MsgException {
        if(query == null){
            throw new MsgException("请求参数为空");
        }
        return ApiResponse.success(applicationService.getPage(query));
    }
}
