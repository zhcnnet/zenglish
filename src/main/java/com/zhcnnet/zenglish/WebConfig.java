package com.zhcnnet.zenglish;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zhcnnet.zenglish.handler.CheckLoginHandler;

@Configuration
public class WebConfig implements WebMvcConfigurer
{
	@Value("${custom.file-upload.server.local}")
	private String path;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) 
	{
		registry.addInterceptor(getCheckLoginHandler())
		.addPathPatterns("/api/**")
		.excludePathPatterns("/api/exception/**")
		.order(1);
	};
	
	@Bean
	public CheckLoginHandler getCheckLoginHandler()
	{
		return new CheckLoginHandler();
	}
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) 
	{
		registry.addResourceHandler("file/**").addResourceLocations("file:" + path);
	}
}