package com.example.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleException {
    //xu li Notfound
    @ExceptionHandler(NotFoundException.class)
    public ErrorMessage handleNotFoundException(NotFoundException e){
        return new ErrorMessage(HttpStatus.NOT_FOUND,e.getMessage());
    }
    //Xu li badrequest
    @ExceptionHandler(BadRequestException.class)
    public ErrorMessage handleBadRequestException(BadRequestException e){
        return new ErrorMessage(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    //xu li cac exception con lai
    @ExceptionHandler(Exception.class)
    public ErrorMessage handleOtherException(Exception e){
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
    }

}
