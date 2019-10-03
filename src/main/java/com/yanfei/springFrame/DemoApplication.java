package com.yanfei.springFrame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.yanfei.springFrame.Config.JwtAuthenticationFilter;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableScheduling
@EnableJpaAuditing
public class DemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean<JwtAuthenticationFilter> jwtFilter() {
		final FilterRegistrationBean<JwtAuthenticationFilter> registrationBean = new FilterRegistrationBean<JwtAuthenticationFilter>();
		JwtAuthenticationFilter filter = new JwtAuthenticationFilter();
		registrationBean.setFilter(filter);
		return registrationBean;
	}
}
