package com.app.e_commerce.controller;


import com.app.e_commerce.Dto.DoneResponce;
import com.app.e_commerce.entity.Product;
import com.app.e_commerce.repository.EcommerceUserRepo;
import com.app.e_commerce.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<DoneResponce> addProductController(@RequestBody Product product){
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }

}
