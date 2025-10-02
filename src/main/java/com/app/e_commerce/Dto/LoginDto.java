package com.app.e_commerce.Dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDto {

    @Schema(name = "email", description = "Enter the user email id", defaultValue = "Tamil@gmail.com")
    private String email;
    @Schema(name = "password", description = "enter the password", defaultValue = "Tamil12345")
    private String password;


}
