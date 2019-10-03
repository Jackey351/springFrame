package com.yanfei.springFrame.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurerAdapter implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new DataSourceIntercetor());
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		String path = new File(Global.getFmsUploadDir()).getParentFile().getAbsolutePath();
//		registry.addResourceHandler("/image/**").addResourceLocations("file:" + path + "/FmsUpload/");
//		registry.addResourceHandler("/tempImage/**").addResourceLocations("file:" + path + "/TempUpload/");
	}
}
