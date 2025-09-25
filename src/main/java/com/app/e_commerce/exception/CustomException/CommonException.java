package com.app.e_commerce.exception.CustomException;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class CommonException extends RuntimeException{

    public CommonException(){}

    public CommonException(String message){
        super(message);
    }

}
