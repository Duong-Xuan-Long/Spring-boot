package com.example.JPASpringboot.entity;

import com.example.JPASpringboot.dto.UserDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@NamedNativeQuery(
        name = "getUserByIdUsingNativeQuery",
        query =
                "select u.id,u.name,u.email from user u where u.id=?1",
        resultSetMapping ="userInfo"
)
@SqlResultSetMapping(
        name = "userInfo",
        classes = @ConstructorResult(
                targetClass = UserDto.class,
                columns = {
                        @ColumnResult(name = "id"),
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "email")
                }
        )
)


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private Integer age;

    @Column(name = "birth")
    private LocalDate birth;
}