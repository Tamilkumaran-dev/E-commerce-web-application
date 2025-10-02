package com.app.e_commerce.Dto;

import com.app.e_commerce.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponce {

    @Schema(name = "message", description = "pass the message", defaultValue = "success")
    private String message;
    @Schema(name = "statusBoolean", description = "pass a boolean for chack is the response is success", defaultValue = "true")
    private boolean statusBoolean;
    @Schema(name = "product", description = "pass the product selected product object", defaultValue = "product object")
    private Optional<Product> product;
    @Schema(name = "isException", description = "Pass the status of the responce in Boolean", defaultValue = "false")
    private Boolean isException = false;

    public ProductResponce(String message, Boolean status, Optional<Product> product){
        this.message = message;
        this.statusBoolean = status;
        this.product = product;
    }

}
