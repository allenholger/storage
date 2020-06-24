package com.example.storage.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
  * spring工具类
  *@author: Allen Holger
 * @date: 2020/6/10 17:25
  */
@Component
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static Object getBean(Class clazz){
        return applicationContext.getBean(clazz);
    }

    public static Object getBean(String name){
        return SpringUtils.applicationContext.getBean(name);
    }
}
