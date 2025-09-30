package com.app.e_commerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String productName;
    private String description;
    private String image;
    private int price;
    private int quantity;

    @UpdateTimestamp
    private LocalDateTime updatedTime;

    @ManyToMany(mappedBy = "cart")
    @JsonIgnore
    private List<ECommerceUser> userAddToCart = new ArrayList<>();


    @ManyToMany(mappedBy = "product")
    @JsonIgnore
    private List<Order> ordered = new ArrayList<>();

}
