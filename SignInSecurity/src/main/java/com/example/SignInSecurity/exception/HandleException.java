package com.example.SignInSecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleException {
    //xu li Notfound
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleNotFoundException(NotFoundException e){
        return new ErrorMessage(HttpStatus.NOT_FOUND,e.getMessage());
    }
    //Xu li badrequest
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleBadRequestException(BadRequestException e){
        return new ErrorMessage(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    //xu li cac exception con lai
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleOtherException(Exception e){
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
    }

}
