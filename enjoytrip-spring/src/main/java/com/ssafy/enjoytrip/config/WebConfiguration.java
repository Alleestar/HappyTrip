package com.ssafy.enjoytrip.config;

import com.ssafy.enjoytrip.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {
	
	private JWTInterceptor jwtInterceptor;

	public WebConfiguration(JWTInterceptor jwtInterceptor) {
		super();
		this.jwtInterceptor = jwtInterceptor;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
//		default 설정.
//		Allow all origins.
//		Allow "simple" methods GET, HEAD and POST.
//		Allow all headers.
//		Set max age to 1800 seconds (30 minutes).
		registry
			.addMapping("/**")
			.allowedOrigins("*")
//			.allowedOrigins("http://localhost:5173", "http://localhost:5174")
			.allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
						HttpMethod.DELETE.name(), HttpMethod.HEAD.name(), HttpMethod.OPTIONS.name(),
						HttpMethod.PATCH.name())
//			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD")
//			.allowCredentials(true)
//			.exposedHeaders("*")
			.maxAge(1800); // Pre-flight Caching
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(jwtInterceptor)
				.addPathPatterns("/enjoytrip/**")
				.excludePathPatterns("/enjoytrip/main/**")
				.excludePathPatterns("/enjoytrip/attraction/**")
				.excludePathPatterns("/enjoytrip/user/**");
//				.excludePathPatterns("/enjoytrip/attraction")
//				.excludePathPatterns("/enjoytrip/user")
		;
	}

//	Swagger UI 실행시 404처리
//	Swagger2 일경우
//	@Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }

}
