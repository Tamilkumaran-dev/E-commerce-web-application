package com.app.e_commerce.Dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.patterns.IToken;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtTokenDto {

    @Schema(name = "message", description = "pass the message", defaultValue = "success")
    private String message;
    private  String token;
    @Schema(name = "data", description = "In this we have to pass the data based on the request", defaultValue = "data as object")
    private Optional<?> data;

    @Schema(name = "statusBoolean", description = "pass a boolean for chack is the response is success", defaultValue = "true")
    private Boolean status = true;
    @Schema(name = "isException", description = "pass a boolean for chack is the response is failed", defaultValue = "false")
    private Boolean isException = false;


    public JwtTokenDto(String token,String message, Optional<?> data) {
        this.token = token;
        this.message = message;
        this.data = data;
    }

}
