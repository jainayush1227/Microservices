package com.springboot.rest.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springboot.rest.application.security.AppProperties;


@SpringBootApplication
public class SpringBootRestApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApplication.class, args);
		
	}
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SpringApplicatoContext springApplicatoContext(){
		return new SpringApplicatoContext();
	}
	
	@Bean(name="AppProperties")
	public AppProperties appProperties(){
		return new AppProperties();
	}

}
