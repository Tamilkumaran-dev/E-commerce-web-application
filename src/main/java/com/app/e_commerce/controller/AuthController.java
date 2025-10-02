package com.app.e_commerce.controller;


import com.app.e_commerce.Dto.JwtTokenDto;
import com.app.e_commerce.Dto.LoginDto;
import com.app.e_commerce.entity.ECommerceUser;
import com.app.e_commerce.service.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Authentication controller",
        description = "This controller is used for user authentication.")
public class AuthController {


    private AuthServiceImpl authService;

    @Operation(
            summary = "Sign up end point",
            description = "This end point is used to add new users"
    )
    @ApiResponse(
            responseCode = "201",
            description = "the user is created"
    )
    @PostMapping("/signup")
    public ResponseEntity<JwtTokenDto> signUpController(@RequestBody ECommerceUser user){

        return new ResponseEntity<>(authService.signUpService(user), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Login end point",
            description = "This end point is used to login the user"
    )
    @ApiResponse(
            responseCode = "200",
            description = "User logged in"
    )
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

    @Operation(
            summary = "Logout end point",
            description = "This end point is used to logout user"
    )
    @ApiResponse(
            responseCode = "202",
            description = "This is used to logout user"
    )
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


}
