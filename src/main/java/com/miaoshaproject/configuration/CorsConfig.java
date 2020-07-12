package com.miaoshaproject.configuration;

import org.springframework.context.annotation.Configuration;

/**
 * 前后端分离跨域配置
 * <p>TODO</p>
 *
 * @author 多宝
 * @since 2020/7/11 13:40
 */

@Configuration
public class CorsConfig {
    /*public CorsConfig(){

    }

    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.setAllowCredentials(true);
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");

        //2.为url添加映射路径
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**",configuration);

        //3.返回重新定义好的coreSource
        return new CorsFilter(corsSource);
    }*/


}
