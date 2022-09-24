package com.example.MidtermEmployee.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name="emailAddress")
    private String emailAddress;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;
}