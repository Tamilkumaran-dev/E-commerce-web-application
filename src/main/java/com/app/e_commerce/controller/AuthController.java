package com.app.e_commerce.controller;


import com.app.e_commerce.Dto.JwtTokenDto;
import com.app.e_commerce.Dto.LoginDto;
import com.app.e_commerce.entity.ECommerceUser;
import com.app.e_commerce.service.AuthServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


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
    public ResponseEntity<JwtTokenDto> loginController(@RequestBody LoginDto login, HttpServletResponse response){

        JwtTokenDto jwtToken = authService.loginService(login);

        Cookie jwtCookie = new Cookie("jwt",jwtToken.getToken());
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(60);

        response.addCookie(jwtCookie);
        return new ResponseEntity<>(authService.loginService(login),HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String,String>> logoutController(HttpServletResponse response){

        Cookie jwtCookie = new Cookie("jwt",null);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(0);

        response.addCookie(jwtCookie);
        Map<String,String> res = new HashMap<>();
        res.put("message","logout successfully");
        return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
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
