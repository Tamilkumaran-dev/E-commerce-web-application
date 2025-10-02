package com.app.e_commerce.Dto;

import com.app.e_commerce.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDto {

    @Schema(name = "userId", description = "We have to pass the userId", defaultValue = "4")
    private Long userId;
    @Schema(name = "cart", description = "We have to  pass the Cart list from the EcommerceUser object", defaultValue = "cart list")
    private List<Product> cart;

}
