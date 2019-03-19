package com.zhh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableCaching
//@ServletComponentScan 开启过滤器
public class SpringBootBaseApplication extends SpringBootServletInitializer {
    public static void main(String args[]){
        SpringApplication.run(SpringBootBaseApplication.class, args);
    }

    @Override
    //为了打包springboot项目添加
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
