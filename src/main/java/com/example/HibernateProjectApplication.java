package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.inject.Inject;



@SpringBootApplication
@EnableWebMvc
public class HibernateProjectApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		SpringApplication.run(HibernateProjectApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "OPTIONS")
						.allowedHeaders("*").allowedOrigins("*").exposedHeaders( "Access-Control-Allow-Origin", "Access-Control-Allow-Methods",
						"Access-Control-Max-Age", "Access-Control-Allow-Headers");
			}
		};
	}



}
