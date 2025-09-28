package com.app.e_commerce.controller;

import com.app.e_commerce.Dto.DoneResponce;
import com.app.e_commerce.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class CartController {

    private ProductService productService;

    @PostMapping("/cart/addToCart/{productId}")
    public ResponseEntity<DoneResponce> addToCartController(@PathVariable("productId") Long productId, HttpServletRequest request){
        return new ResponseEntity<>(productService.addToCart(productId,request), HttpStatus.CREATED);
    }

}

