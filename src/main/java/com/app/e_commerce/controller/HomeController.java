package com.app.e_commerce.controller;


import com.app.e_commerce.Dto.DoneResponce;
import com.app.e_commerce.Dto.LoginDto;
import com.app.e_commerce.Dto.ProductResponce;
import com.app.e_commerce.entity.Product;
import com.app.e_commerce.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
public class HomeController {

    private ProductService productService;

    @GetMapping("/home/{page}/{size}")
    public ResponseEntity<Page<Product>> homePage(@PathVariable("page") int page,@PathVariable("size") int size){
        return new ResponseEntity<>(productService.getAllProduct(page,size), HttpStatus.OK);
    }

    @PostMapping("/home/test")
    public String testPost(@RequestBody LoginDto loginDto){
        return "test";
    }

    @GetMapping("/home/product/{productId}")
    public ResponseEntity<ProductResponce> getProduct(@PathVariable("productId") Long productId){
        return new ResponseEntity<>(productService.getProduct(productId),HttpStatus.OK);
    }

    @GetMapping("/home/searchProduct/{keyword}/{page}/{size}")
    public ResponseEntity<Page<Product>> searchProduct(@PathVariable("keyword") String keyword,@PathVariable("page") int page,@PathVariable("size") int size){
        return new ResponseEntity<>(productService.searchProduct(keyword,page,size),HttpStatus.OK);
    }


}
