package com.example.MidtermEmployee.entity;

import com.example.MidtermEmployee.dto.UserDto;
import com.example.MidtermEmployee.dto.UserInfo;
import lombok.*;

import javax.persistence.*;

@NamedNativeQuery(
        name = "getUserByIdUsingNativeQuery",
        query =
                "select u.id,u.name,u.email from user u where u.id=?1",
        resultSetMapping ="userDto"
)
@SqlResultSetMapping(
        name = "userDto",
        classes = @ConstructorResult(
                targetClass = UserDto.class,
                columns = {
                        @ColumnResult(name = "id",type=Long.class),
                        @ColumnResult(name = "name",type=String.class),
                        @ColumnResult(name = "email",type=String.class)
                }
        )
)



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
}
