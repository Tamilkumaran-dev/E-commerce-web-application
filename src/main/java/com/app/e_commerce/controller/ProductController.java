package com.app.e_commerce.controller;


import com.app.e_commerce.Dto.DoneResponce;
import com.app.e_commerce.entity.Product;
import com.app.e_commerce.repository.EcommerceUserRepo;
import com.app.e_commerce.service.ProductService;
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
@RequestMapping("/product")
@Tag(name = "Product controller",
        description = "This controller is used for Product based operation")
public class ProductController {

    private ProductService productService;


    @Operation(
            summary = "Add new product end point",
            description = "Using this end point admin can add product"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Create a new product"
    )
    @PostMapping("/addProduct")
    public ResponseEntity<DoneResponce> addProductController(@RequestBody Product product){
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update product end point",
            description = "Using this end point admin can update the the product"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Update the product"
    )
    @PutMapping("/update/{productId}")
    public ResponseEntity<DoneResponce> updateProduct(@PathVariable("productId") Long productId, @RequestBody Product product){
        return new ResponseEntity<>(productService.updateProduct(productId,product),HttpStatus.CREATED);
    }

}
