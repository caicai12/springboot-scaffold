package com.zhh.config;

import com.zhh.common.auth.AuthInterceptor;
import com.zhh.common.auth.IdentityInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Description 权限配置
 * @Author zhouhui
 * @Version V1.0
 * @Date 2018/12/20 19:02
 */
@Configuration
public class AuthConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new IdentityInterceptor())//指定拦截器类
                .addPathPatterns("/**");

        registry.addInterceptor(new AuthInterceptor())//指定拦截器类
                .addPathPatterns("/user/*");
    }

    //注意：加入拦截器后，swagger不能访问，还须重写以下方法才能解决
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        //加入以下配置，开通静态文件访问权限
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}
