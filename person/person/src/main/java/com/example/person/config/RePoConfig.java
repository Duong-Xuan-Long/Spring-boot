package com.example.person.config;

import com.example.person.repository.PersonMethods;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RePoConfig {
    @Bean
    public PersonMethods personMethods(){
    return new PersonMethods("person.csv");
    }
}
