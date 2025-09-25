package com.app.e_commerce.controller;


import com.app.e_commerce.Dto.LoginDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HomeController {


    @GetMapping("/home")
    public String homePage(){
        return "Home";
    }

    @PostMapping("/home/test")
    public String testPost(@RequestBody LoginDto loginDto){
        return "test";
    }

}
