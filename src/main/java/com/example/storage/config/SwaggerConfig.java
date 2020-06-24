package com.example.storage.config;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 本类是Swagger的配置类
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"com.example.storage.controller"})
public class SwaggerConfig {

    //组织Docket对象，翻译过来就是摘要的意思，它是生成API文档的核心对象，里面配置一些必要的信息
    @Bean
    public Docket swaggerSpringMvcPlugin(){
        ////指定规范，这里是SWAGGER_2
        return new Docket(DocumentationType.SWAGGER_2)
                ////设定Api文档头信息，这个信息会展示在文档UI的头部位置
                .apiInfo(customApiInfo())
                .host("localhost:8080")
                .protocols(Sets.newHashSet("http", "https"))
                .select()
                //只生成被Api这个注解注解过的类接口
                //.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //只生成被ApiOperation这个注解注解过的api接口
                //.apis(RequestHandlerSelectors.withClassAnnotation(ApiOperation.class))
                //生成所有API接口
                .apis(RequestHandlerSelectors.basePackage("com.example.storage.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    //自定义API文档基本信息实体
    private ApiInfo customApiInfo(){
        return new ApiInfoBuilder()
                //文档标题
                .title("Swagger2构建RESTful API文档")
                //版本
                .version("V1.0")
                //协议许可
                .license("SNAPSHOT")
                //描述
                .description("存储")
                .contact("Allenholger")
                .build();
    }

}
