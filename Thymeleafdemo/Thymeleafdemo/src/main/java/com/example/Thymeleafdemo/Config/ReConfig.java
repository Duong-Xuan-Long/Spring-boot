package com.example.Thymeleafdemo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.Thymeleafdemo.repository.PersonMethods;

@Configuration
public class ReConfig {
    @Bean
    public PersonMethods personMethods(){
        return new PersonMethods("person1.csv");
    }
}
