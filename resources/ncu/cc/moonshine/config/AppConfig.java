package ncu.cc.moonshine.config;

import ncu.cc.moonshine.services.IUserService;
import ncu.cc.moonshine.services.UserServiceArrayListImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	private @Value("${jdbc.url}") String url;
	private @Value("${jdbc.username}") String username;
	private @Value("${jdbc.password}") String password;
	
	@Bean
	public IUserService userService() {
		return new UserServiceArrayListImpl();
	}
}
