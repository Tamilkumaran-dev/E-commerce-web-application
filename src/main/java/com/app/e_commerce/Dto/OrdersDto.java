package com.app.e_commerce.Dto;

import com.app.e_commerce.entity.ECommerceUser;
import com.app.e_commerce.entity.Product;
import com.app.e_commerce.entity.UserPersonalDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrdersDto {

    @Schema(name = "id",description = "Enter the id of the order", defaultValue = "1")
    private Long id;

    @Schema(name = "date", description = "The date will automatically inserted", defaultValue = "ne needed")
    private LocalDateTime date;

    @Schema(name = "buyerName", description = "enter the buyers name",defaultValue = "Tamil")
    private String buyerName;

    @Schema(name = "buyer", description = " enter the UserPersonalDetail object", defaultValue = "UserPersonalDetail object")
    private UserPersonalDetail buyer;

    @Schema(name = "product", description = "pass all the products in the customer orders", defaultValue = "product list")
    private List<Product> product = new ArrayList<>();

    @Schema(name = "status", description = "set the status of the order", defaultValue = "order-placed")
    private String status;

}
