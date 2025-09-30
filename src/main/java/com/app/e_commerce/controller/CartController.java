package com.app.e_commerce.controller;

import com.app.e_commerce.Dto.DoneResponce;
import com.app.e_commerce.Dto.JwtTokenDto;
import com.app.e_commerce.service.ProductService;
import com.app.e_commerce.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {

   private UserService userService;

    @PostMapping("/addToCart/{productId}")
    public ResponseEntity<DoneResponce> addToCartController(@PathVariable("productId") Long productId, HttpServletRequest request){
        return new ResponseEntity<>(userService.addToCart(productId,request), HttpStatus.CREATED);
    }

    @GetMapping("/userCart")
    public ResponseEntity<JwtTokenDto> getUserCartcontroller(HttpServletRequest request){
        return new ResponseEntity<>(userService.getUserCart(request),HttpStatus.OK);
    }

    @PutMapping("/removeProduct/{userId}/{productId}")
    public ResponseEntity<DoneResponce> removeProductFromCart(@PathVariable("userId") Long userId,@PathVariable("productId") Long productId){
        return new ResponseEntity<>(userService.removeProduct(userId,productId),HttpStatus.OK);
    }

}

