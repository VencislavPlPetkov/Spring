package com.ve.codeinterceptor.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import net.bytebuddy.asm.Advice.This;

@Configuration
public class MVCConfig implements WebMvcConfigurer {

	private final PrependTitleAndLogMessageInterceptor prependTitleAndLogMessageInterceptor;
	private final CaptchaInterceptor captchaInterceptor;
	
	
	
	
	@Autowired
	public MVCConfig(PrependTitleAndLogMessageInterceptor prependTitleAndLogMessageInterceptor, 
			CaptchaInterceptor captchaInterceptor) {

		this.prependTitleAndLogMessageInterceptor = prependTitleAndLogMessageInterceptor;
		this.captchaInterceptor = captchaInterceptor;
	}
	
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(this.prependTitleAndLogMessageInterceptor);
		registry.addInterceptor(this.captchaInterceptor);
		
	}
	
	
	
	
	
}



















