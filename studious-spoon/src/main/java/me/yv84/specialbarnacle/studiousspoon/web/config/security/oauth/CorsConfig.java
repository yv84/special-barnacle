package me.yv84.specialbarnacle.studiousspoon.web.config.security.oauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
	    	.allowCredentials(true)
	    	.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTION")
	    	.allowedOrigins("*")
	    	.allowedHeaders("*")
	    	.maxAge(1000);
    }

}
