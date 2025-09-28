package com.app.e_commerce.controller;

import com.app.e_commerce.Dto.DoneResponce;
import com.app.e_commerce.Dto.JwtTokenDto;
import com.app.e_commerce.repository.EcommerceUserRepo;
import com.app.e_commerce.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private UserService userService;

    @GetMapping("")
    public ResponseEntity<JwtTokenDto> getUserProfile(HttpServletRequest request){
        return new ResponseEntity<>(userService.getUserProfile(request), HttpStatus.OK);
    }

    @PostMapping("/isLoggedIn")
    public ResponseEntity<DoneResponce> isLoggedIn(){
        return new ResponseEntity<>(userService.isLoggedInService(),HttpStatus.OK);
    }

}
