package com.example.storage.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 本类是系统日志的注解类
 * @author Allen Holger
 */
@Target({ElementType.TYPE, ElementType.METHOD,ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String name() default "";
}
