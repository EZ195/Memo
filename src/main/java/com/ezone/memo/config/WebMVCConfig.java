package com.ezone.memo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ezone.memo.common.FileManagerService;
import com.ezone.memo.common.PermissionInterceptor;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer{
	
	@Autowired
	private PermissionInterceptor interceptor;
	
	// 이 클래스는 외부에서의 접근을 막아둔 보안을 특정 경로만 지정해 접근을 뚫어주는 기능을 함
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// C:\\Users\\EZi09\\Desktop\\ezone\\memo\\upload\\images/~ 
		
		registry.addResourceHandler("/images/**")
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH); // public static 사용해서 
		
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor)
		.addPathPatterns("/**")	// 어떤 path가 인터셉터를 거쳐서 수행될지 설정
		.excludePathPatterns("/staic/**" , "/images/**" , "/user/sign_out"); // 처리하지 않을 path
		
	}

}
