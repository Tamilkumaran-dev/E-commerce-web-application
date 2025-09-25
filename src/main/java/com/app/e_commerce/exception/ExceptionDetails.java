package com.app.e_commerce.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDetails {

    private String message;
    private String errorCode;
    private String path;
    private Boolean isException;


}
