package com.app.e_commerce.controller;


import com.app.e_commerce.Dto.DoneResponce;
import com.app.e_commerce.Dto.LoginDto;
import com.app.e_commerce.Dto.ProductResponce;
import com.app.e_commerce.entity.Product;
import com.app.e_commerce.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@Tag(name = "Home controller",
        description = "This controller is used for home page operation")
public class HomeController {

    private ProductService productService;

    @Operation(
            summary = "Home end point",
            description = "This end point is used to get all product to display in the home"
    )
    @ApiResponse(
            responseCode = "200",
            description = "get all product to display in home page"
    )
    @GetMapping("/home/{page}/{size}")
    public ResponseEntity<Page<Product>> homePage(@PathVariable("page") int page,@PathVariable("size") int size){
        return new ResponseEntity<>(productService.getAllProduct(page,size), HttpStatus.OK);
    }

    @Operation(
            summary = "Sign up end point",
            description = "This end point is used to add new users"
    )
    @ApiResponse(
            responseCode = "201",
            description = "the user is created"
    )
    @PostMapping("/home/test")
    public String testPost(@RequestBody LoginDto loginDto){
        return "test";
    }

    @Operation(
            summary = "Get specific product endpoint",
            description = "get specific product for viewing product"
    )
    @ApiResponse(
            responseCode = "200",
            description = "get specific product"
    )
    @GetMapping("/home/product/{productId}")
    public ResponseEntity<ProductResponce> getProduct(@PathVariable("productId") Long productId){
        return new ResponseEntity<>(productService.getProduct(productId),HttpStatus.OK);
    }

    @Operation(
            summary = "search for product end point",
            description = "search for product by name of the product"
    )
    @ApiResponse(
            responseCode = "200",
            description = "search product"
    )
    @GetMapping("/home/searchProduct/{keyword}/{page}/{size}")
    public ResponseEntity<Page<Product>> searchProduct(@PathVariable("keyword") String keyword,@PathVariable("page") int page,@PathVariable("size") int size){
        return new ResponseEntity<>(productService.searchProduct(keyword,page,size),HttpStatus.OK);
    }


}
