package com.app.e_commerce.Dto;

import com.app.e_commerce.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDto {

    private Long userId;
    private List<Product> cart;

}
