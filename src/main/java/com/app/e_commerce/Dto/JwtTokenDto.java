package com.app.e_commerce.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.patterns.IToken;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtTokenDto {

    private String message;
    private  String token;
    private Optional<?> data;
    private Boolean status = true;
    private Boolean isException = true;


    public JwtTokenDto(String token,String message, Optional<?> data) {
        this.token = token;
        this.message = message;
        this.data = data;
    }
}
