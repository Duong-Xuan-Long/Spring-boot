package com.example.MidtermEmployee;

import com.github.javafaker.Faker;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class MidtermEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MidtermEmployeeApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	@Bean
	public Faker faker(){
		return new Faker();
	}

	@Bean
	public Random random(){
		return new Random();
	}
}
