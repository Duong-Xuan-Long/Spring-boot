package com.example.Thymeleafdemo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @JsonIgnore
    private int id;
    private String name;
    private String email;
    private String department;

    public Person(String name, String email, String department) {
        this.name=name;
        this.email=email;
        this.department=department;
    }
}
