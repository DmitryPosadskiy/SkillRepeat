package dev.posadskiy.skillrepeat;

import dev.posadskiy.skillrepeat.annotation.bpp.SecurityAnnotationBeanPostProcessor;
import dev.posadskiy.skillrepeat.controller.SessionController;
import dev.posadskiy.skillrepeat.controller.SessionControllerImpl;
import dev.posadskiy.skillrepeat.controller.UserController;
import dev.posadskiy.skillrepeat.controller.UserControllerImpl;
import dev.posadskiy.skillrepeat.mapper.AuthMapper;
import dev.posadskiy.skillrepeat.mapper.AuthMapperImpl;
import dev.posadskiy.skillrepeat.mapper.UserMapper;
import dev.posadskiy.skillrepeat.mapper.UserMapperImpl;
import dev.posadskiy.skillrepeat.validator.AuthValidator;
import dev.posadskiy.skillrepeat.validator.UserValidator;
import dev.posadskiy.skillrepeat.worker.OldSessionGarbageCollectorWorker;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class SpringConfiguration {

	@Bean
	public UserController userController() {
		return new UserControllerImpl();
	}

	@Bean
	public SessionController sessionController() {
		return new SessionControllerImpl();
	}

	@Bean
	public UserMapper userMapper() {
		return new UserMapperImpl();
	}

	@Bean
	public AuthMapper authMapper() {
		return new AuthMapperImpl();
	}

	@Bean
	public AuthValidator authValidator() {
		return new AuthValidator();
	}

	@Bean
	public UserValidator userValidator() {
		return new UserValidator();
	}

	@Bean
	public SecurityAnnotationBeanPostProcessor securityAnnotationBeanPostProcessor() {
		return new SecurityAnnotationBeanPostProcessor();
	}

	@Bean
	public OldSessionGarbageCollectorWorker oldSessionGarbageCollector() {
		return new OldSessionGarbageCollectorWorker();
	}

	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}
}
