package com.example.Securitydemo.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
@TypeDef(
        name="json",
        typeClass = JsonStringType.class
)
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id",nullable=false)
    private Integer id;

    @Column(name="name",nullable=false)
    private String name;

    @Column(name="email",nullable=false,unique = true)
    private String email;

    @Column(name="password",nullable=false)
    private String password;

    @Type(type="json")
    @Column(name="roles",nullable=false,columnDefinition = "json")
    private List<String> roles;
}
