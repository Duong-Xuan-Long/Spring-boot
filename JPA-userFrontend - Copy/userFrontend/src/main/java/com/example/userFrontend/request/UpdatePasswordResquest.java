package com.example.userFrontend.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordResquest {
    @NotEmpty(message ="oldPassword khong duoc de trong")
    @NotBlank(message ="oldPassword khong duoc de trong")
    private String oldPassword;

    @NotEmpty(message ="newPassword khong duoc de trong")
    @NotBlank(message = "newPassword khong duoc de trong")
    @Length(min=4,message="Do dai it nhat la 4" )
    private String newPassword;
}
