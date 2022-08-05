package com.example.student.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
