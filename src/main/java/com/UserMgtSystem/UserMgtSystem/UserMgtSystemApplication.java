package com.UserMgtSystem.UserMgtSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication 
@ComponentScan (basePackages = "com.UserMgtSystem.UserMgtSystem.ServiceImpl"+"com.UserMgtSystem.UserMgtSystem.Controller")
public class UserMgtSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserMgtSystemApplication.class, args);
		
	}
	
	}


