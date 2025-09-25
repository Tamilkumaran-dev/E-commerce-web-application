package com.app.e_commerce.controller;


import com.app.e_commerce.Dto.JwtTokenDto;
import com.app.e_commerce.Dto.LoginDto;
import com.app.e_commerce.entity.ECommerceUser;
import com.app.e_commerce.service.AuthServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {


    private AuthServiceImpl authService;

    @PostMapping("/signup")
    public ResponseEntity<JwtTokenDto> signUpController(@RequestBody ECommerceUser user){

        return new ResponseEntity<>(authService.signUpService(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> loginController(@RequestBody LoginDto login){

        return new ResponseEntity<>(authService.loginService(login),HttpStatus.OK);
    }

    @PostMapping("/test")
    public String testPost(@RequestBody LoginDto loginDto){
        return "test";
    }
    @GetMapping("/test")
    public String testget(){
        return "test";
    }

}
