package com.example.userFrontend.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandleException extends ResponseEntityExceptionHandler {
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
    //handle
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> details = new HashMap<>();
        for(FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            details.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, details);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}
