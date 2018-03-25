package com.bcdigger.core.config;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.bcdigger.admin.interceptor.AdminLoginInterceptor;
/**
 * 配置管理员登录拦截器
 * @author yookien
 *
 */
@Configuration  
@EnableWebMvc  
@ComponentScan(basePackages = "com.digger.*.controller")  
@PropertySource(value = "classpath:application.yml",  
        ignoreResourceNotFound = true,encoding = "UTF-8") 
public class WebSecurityConfig extends WebMvcConfigurerAdapter {
	
	private static final Logger logger = Logger.getLogger(WebSecurityConfig.class);
    @Autowired  
    private AdminLoginInterceptor adminLoginInterceptor;  
  
    /** 
     * <p> 
     *     视图处理器 
     * </p> 
     * 
     * @return 
     */  
    /*@Bean  
    public ViewResolver viewResolver() {  
        logger.info("ViewResolver");  
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();  
        viewResolver.setPrefix("/templates/");  
        viewResolver.setSuffix(".ftl");  
        return viewResolver;  
    }*/
    
    /** 
     * 拦截器配置 
     * @param registry 
     */  
    @Override  
    public void addInterceptors(InterceptorRegistry registry) {  
        // 注册监控拦截器  
        registry.addInterceptor(adminLoginInterceptor)  
                .addPathPatterns("/admin/**")  
         .excludePathPatterns("/public/")
         .excludePathPatterns("/static/")
         .excludePathPatterns("/**/*.css")
         .excludePathPatterns("/**/*.js");
        
  
    }  
  
    /** 
     * 资源处理器 
     * @param registry 
     */  
    @Override  
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
    	registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
    	registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
    	registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/fonts/");
    	registry.addResourceHandler("/html/**").addResourceLocations("classpath:/static/html/");
    	registry.addResourceHandler("/vendors/**").addResourceLocations("classpath:/static/vendors/");
    	registry.addResourceHandler("/build/**").addResourceLocations("classpath:/static/build/");
    	registry.addResourceHandler("/index.html").addResourceLocations("classpath:/templates/index.html");
        super.addResourceHandlers(registry);
    }  

}
