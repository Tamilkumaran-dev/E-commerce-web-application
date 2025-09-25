package com.app.e_commerce.exception;

import com.app.e_commerce.exception.CustomException.CommonException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GllobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ExceptionDetails> commonException(CommonException exception, WebRequest webRequest){

        ExceptionDetails exceptionDetails = new ExceptionDetails(
                exception.getMessage(),
                "COMMON_EXCEPTION",
                webRequest.getDescription(false),
                true
        );
        return new ResponseEntity<>(exceptionDetails,HttpStatus.CONFLICT);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetails> defaultException(Exception exception, WebRequest webRequest){

        ExceptionDetails exceptionDetails = new ExceptionDetails(
                exception.getMessage(),
                "DEFAULT_EXCEPTION",
                webRequest.getDescription(false),
                true
        );

        return new ResponseEntity<>(exceptionDetails,HttpStatus.CONFLICT);
    }
}
