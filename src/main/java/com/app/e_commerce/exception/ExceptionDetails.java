package com.app.e_commerce.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDetails {

    public ExceptionDetails(String message, String errorCode, String path, Boolean isException) {
        this.message = message;
        this.errorCode = errorCode;
        this.path = path;
        this.isException = isException;
    }

    private String message;
    private String errorCode;
    private String path;
    private Boolean isException;
    private Boolean status = false;



}
