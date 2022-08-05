package com.example.student.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message){
        super(message);
    }
}
