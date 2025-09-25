package com.app.e_commerce.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtTokenDto {

    private String token;
    private String message;
    private Optional<?> data;

}
