package com.example.BackEndStandBlog;

import com.github.javafaker.Faker;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class BackEndStandBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEndStandBlogApplication.class, args);
	}
	@Bean
	public Faker faker(){
		return new Faker();
	}
	@Bean
	public Random random(){
		return new Random();
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
