package com.app.e_commerce.controller;

import com.app.e_commerce.Dto.DoneResponce;
import com.app.e_commerce.Dto.JwtTokenDto;
import com.app.e_commerce.service.ProductService;
import com.app.e_commerce.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/cart")
@Tag(name = "Cart controller",
        description = "This controller is used for user Cart.")
public class CartController {

   private UserService userService;

    @Operation(
            summary = "Add to cart end point",
            description = "This end point is used to add product to cart"
    )
    @ApiResponse(
            responseCode = "201",
            description = "product is added"
    )
    @PostMapping("/addToCart/{productId}")
    public ResponseEntity<DoneResponce> addToCartController(@PathVariable("productId") Long productId, HttpServletRequest request){
        return new ResponseEntity<>(userService.addToCart(productId,request), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get car end point",
            description = "This end point is used to get user cart"
    )
    @ApiResponse(
            responseCode = "200",
            description = "This is used to get user cart"
    )
    @GetMapping("/userCart")
    public ResponseEntity<JwtTokenDto> getUserCartcontroller(HttpServletRequest request){
        return new ResponseEntity<>(userService.getUserCart(request),HttpStatus.OK);
    }

    @Operation(
            summary = "Remove product from cart end point",
            description = "This end point is used to remove user cart"
    )
    @ApiResponse(
            responseCode = "200",
            description = "remove product"
    )
    @PutMapping("/removeProduct/{userId}/{productId}")
    public ResponseEntity<DoneResponce> removeProductFromCart(@PathVariable("userId") Long userId,@PathVariable("productId") Long productId){
        return new ResponseEntity<>(userService.removeProduct(userId,productId),HttpStatus.OK);
    }

}

