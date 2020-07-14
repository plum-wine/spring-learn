package com.github.web.config;

import com.github.web.SpringBootWebApplication;
import com.github.web.handler.SessionResolver;
import com.github.web.interceptors.AccessInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author ZhangHang
 * @date 18-7-1
 * *****************
 * @function: 继承了该类之后 所有web配置自动失效
 */
@Configuration
@ComponentScan(basePackageClasses = SpringBootWebApplication.class, useDefaultFilters = true)
public class WebConfiguration implements WebMvcConfigurer {

    private SessionResolver sessionResolver;

    @Autowired
    public WebConfiguration(SessionResolver sessionResolver) {
        this.sessionResolver = sessionResolver;
    }

    /**
     * 配置web静态资源位置
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AccessInterceptor())
                // 拦截
                .addPathPatterns("/**")
                // 不拦截
                .excludePathPatterns("/no");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(sessionResolver);
    }

}
