package com.example.Web_Ecommerce.utils;

import com.example.Web_Ecommerce.request.RegisterRequest;

import java.util.HashMap;
import java.util.Map;

public class ValidateObject {
    //kiểm tra phần đăng kí
    public static Map<String, String> validateRegister(RegisterRequest registerRequest) {
        Map<String, String> errors = new HashMap<>();

        //kiểm tra email
        errors.putAll(ValidateUtils.builder()
                .fieldname("email")
                .value(registerRequest.getEmail())
                .emailRegex(true).build().validate());
        //kiểm tra mật khẩu
        errors.putAll(ValidateUtils.builder()
                .fieldname("password")
                .value(registerRequest.getPassword())
                .passwordRegex(true).build().validate());
        return errors;
    }
    public static Map<String, String> validateUpdate(String password) {
        Map<String, String> errors = new HashMap<>();
        //kiểm tra mật khẩu
        errors.putAll(ValidateUtils.builder()
                .fieldname("password")
                .value(password)
                .passwordRegex(true).build().validate());
        return errors;
    }
}
