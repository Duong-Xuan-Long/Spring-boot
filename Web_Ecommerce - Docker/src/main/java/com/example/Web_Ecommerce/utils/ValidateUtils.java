package com.example.Web_Ecommerce.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;


import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//taọ class validateUtils
@Builder
public class ValidateUtils {
    private String fieldname;
    private Object value;
    private boolean required;

    private boolean emailRegex;

    private boolean passwordRegex;


    private final String EMAIL_REGEX="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private final String PASSWORD_REGEX="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    public Map validate(){
        Map<String,String> errors=new HashMap<>();

        if(required && ObjectUtils.isEmpty(value) &&!ObjectUtils.isEmpty(fieldname)){
            errors.put(fieldname, fieldname+ " không được trống");
        }
        //kiểm tra phần email
        if(emailRegex && !ObjectUtils.isEmpty(value) && !ObjectUtils.isEmpty(fieldname)){
            Pattern pattern=Pattern.compile(EMAIL_REGEX);
            Matcher matcher=pattern.matcher(value.toString());
            if(!matcher.matches()){
                errors.put(fieldname," Email không đúng dạng");
            }
        }
        //kiểm tra phần mật khẩu
        if(passwordRegex && !ObjectUtils.isEmpty(value)&& !ObjectUtils.isEmpty(fieldname)){
            Pattern patern=Pattern.compile(PASSWORD_REGEX);
            Matcher matcher=patern.matcher(value.toString());
            if(!matcher.matches()){
                errors.put(fieldname," Mật khẩu phải có ít nhất: một chữ số 0-9, " +
                        "một kí tự viêt hoa và viết thường, một trong các kí tự đặc biệt '! @ # & ( ).' , " +
                        "độ dài mật khẩu tối thiểu là 8 và tối đa là 20");
            }
        }
        return errors;
    }


}
