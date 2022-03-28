package com.ezone.memo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ezone.memo.common.FileManagerService;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer{
	// 이 클래스는 외부에서의 접근을 막아둔 보안을 특정 경로만 지정해 접근을 뚫어주는 기능을 함
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// C:\\Users\\EZi09\\Desktop\\ezone\\memo\\upload\\images/~ 
		
		registry.addResourceHandler("/images/**")
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH); // public static 사용해서 
		
	}

}
