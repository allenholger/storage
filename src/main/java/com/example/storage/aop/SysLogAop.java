package com.example.storage.aop;

import com.example.storage.annotation.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
  *系统日志的切面类
  *@author: Allen Holger
  *@since: 2020/5/29
  */
//@Component
@Aspect
@Slf4j
public class SysLogAop {

    /**
     * 切点的定义：
     *  切点时使用@Poincut注解 括号中是execution表达式
     *  第一个 表示的是返回值的类型， *表示所有类型
     *  *。*。*。service.. 是包名, 它表示service包及其子包，在本项目中表示的是com.example.storage.service和com.example.storage.service.serviceImpl包
     *  * 表示的是类名，指的是controller下的所有类
     *  * 表示的类中的方法，*指的是所有方法
     *  （。。）表示方法参数，。。表示任何参数
     */
    //定义service层的切点
    @Pointcut("@annotation(com.example.storage.annotation.SysLog)")
    private void logAspect(){};

    //前置通知
    @Before(value = "logAspect()")
    public void doBefore(JoinPoint joinPoint){
        if(log.isInfoEnabled()){
            log.info("前置通知：" + joinPoint.toShortString());
        }
    }

    //环绕通知
    @Around(value = "logAspect()")
    public void doAround(JoinPoint joinPoint) {
        //获取开始的时间
        Long startTime = System.currentTimeMillis();
        try {
            ((ProceedingJoinPoint)joinPoint).proceed();
            Long endTime = System.currentTimeMillis();
            if(log.isInfoEnabled()){
                log.info("环绕通知：{} 使用的时间 {} ms！", joinPoint.toShortString(), (endTime - startTime));
            }
        } catch (Throwable throwable) {
            Long endTime = System.currentTimeMillis();
            if(log.isInfoEnabled()){
                log.info("环绕通知：{} 出现了异常 {}，使用的时间 {} ms！", joinPoint.toShortString(), throwable.getMessage(), (endTime - startTime));
            }
        }
    }

    //后置通知
    @After(value = "logAspect()")
    public void doAfter(JoinPoint joinPoint){
        //类名
        String className = joinPoint.getTarget().getClass().getName();
        //方法名
        String methodName = joinPoint.getSignature().getName();
        Object[] paramters = joinPoint.getArgs();
        String operationName = "";
        //获取所有的方法
        Method[] methods = joinPoint.getTarget().getClass().getMethods();
        for(Method method: methods){
            if(method.getName().equalsIgnoreCase(methodName) && method.getParameters().length == paramters.length){
                operationName = method.getAnnotation(SysLog.class).name();
                break;
            }
        }
        if(log.isInfoEnabled()){
            log.info("后置通知：{}的{}方法被执行，系统日志名{}", className, methodName, operationName);
        }
    }

    /*
    //后置的返回通知
    @AfterReturning(pointcut = "logAspect()", returning = "ret")
    public void doAfterReturning(Object ret){
        if(ret == null){
            log.info("后置返回通知：没有返回值");
        }else{
            log.info("后置返回通知：返回值为：{}", ret);
        }
    }

    //后置异常通知
    @AfterThrowing(pointcut = "logAspect()", throwing = "thr")
    public void doAfterThrowing(Throwable thr, JoinPoint joinPoint){
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        if(log.isInfoEnabled()){
            log.info("后置异常通知：{}类中{}方法在执行时出现了异常，异常信息{}!", className, methodName, thr.getMessage());
        }
    }
*/
}
