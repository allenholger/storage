package com.example.storage.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis的配置类
 * @author Allen Holger
 */
@Component
@MapperScan("com.example.storage.mapper")
@EnableConfigurationProperties(MybatisPlusProperties.class)
@EnableTransactionManagement
public class MybatisConfig {

    @Autowired
    private MybatisPlusProperties mybatisPlusProperties;

    @Bean
    @Primary
    public MybatisPlusProperties mybatisPlusProperties(){
        return mybatisPlusProperties;
    }

    @Bean
    @ConditionalOnMissingBean(value = PaginationInterceptor.class)
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
