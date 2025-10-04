package com.app.e_commerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(name = "productName", description = "Enter the product name" ,defaultValue = "ASUS TUF a15")
    private String productName;

    @Schema(name = "description", description = "Enter the product description" ,defaultValue = "Asus tuf gaming laptop")
    @Column(length = 2000) // allows up to 2000 characters
    private String description;

    @Schema(name = "image", description = "Enter the image url", defaultValue = "https://laptopmedia.com/wp-content/uploads/2021/05/3-69.jpg")
    private String image;

    @Schema(name = "price", description = "Enter the price of the product", defaultValue = "70000")
    private int price;
    private int quantity;

    @Schema(name = "updatedTime", description = "Product added time", defaultValue = "The value will be added automatically")
    @UpdateTimestamp
    private LocalDateTime updatedTime;

    @Schema(name = "userAddToCart", description = "This is used to many to many relation with user EcommerceUser", defaultValue = "No need")
    @ManyToMany(mappedBy = "cart")
    @JsonIgnore
    private List<ECommerceUser> userAddToCart = new ArrayList<>();


    @Schema(name = "ordered", description = "This is used to many to many relation with order entity",defaultValue = "no need")
    @ManyToMany(mappedBy = "product")
    @JsonIgnore
    private List<Order> ordered = new ArrayList<>();

}
