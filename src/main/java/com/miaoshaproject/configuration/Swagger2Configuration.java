package com.miaoshaproject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置类
 * <p>TODO</p>
 *
 * @author 多宝
 * @since 2020/7/4 22:34
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    @Bean
    public Docket swaggerconfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(info())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.miaoshaproject.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo info(){
        return new ApiInfoBuilder()
                .title("亿万级流量秒杀系统后端接口")
                .description("亿万级流量秒杀项目学习")
                .termsOfServiceUrl("localhost:8090")
                .contact(new Contact("多宝", "localhost:8090", "y908063221@gmail.com"))
                .version("v1.0")
                .build();
    }
}
