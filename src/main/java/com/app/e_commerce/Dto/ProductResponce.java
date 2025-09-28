package com.app.e_commerce.Dto;

import com.app.e_commerce.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponce {

    private String message;
    private boolean statusBoolean;
    private Optional<Product> product;
    private Boolean isException = false;

    public ProductResponce(String message, Boolean status, Optional<Product> product){
        this.message = message;
        this.statusBoolean = status;
        this.product = product;
    }

}
