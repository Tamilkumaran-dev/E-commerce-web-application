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
@Table(name = "orders")
public class Order{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(name = "date", description = "the will store the order placed date and time",defaultValue = "Set by default")
    @UpdateTimestamp
    private LocalDateTime date;

    @Schema(name = "buyerId", description = "set the user id who place the order" , defaultValue = "2")
    private Long buyerId;

    @Schema(name = "buyer", description = "pass the EcommerceUser object of the user", defaultValue = "Object of the user")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buyersOrder", referencedColumnName = "id")
    @JsonIgnore
    private ECommerceUser buyer;


    @Schema(name = "product", description = "We have to pass the products which the customer ordered", defaultValue = "List of product")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ordered_product",  // table name (snake_case is convention)
            joinColumns = @JoinColumn(name = "order_id"),       // FK to Order
            inverseJoinColumns = @JoinColumn(name = "product_id") // FK to Product
    )
    private List<Product> product = new ArrayList<>();


    @Schema(name = "status", description = "We have to status", defaultValue = "order-placed")
    private String status;


}


