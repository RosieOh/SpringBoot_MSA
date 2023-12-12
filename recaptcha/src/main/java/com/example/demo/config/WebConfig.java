package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.Interceptor.Interceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Autowired
	private Interceptor interceptor;
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	
    	registry.addInterceptor(interceptor)
    			.addPathPatterns("/","/*")
    			.excludePathPatterns("/error","/login");
    	
    }

}
