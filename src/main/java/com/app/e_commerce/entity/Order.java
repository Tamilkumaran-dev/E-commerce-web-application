package com.app.e_commerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private LocalDateTime date;

    private Long buyerId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buyersOrder", referencedColumnName = "id")
    @JsonIgnore
    private ECommerceUser buyer;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ordered_product",  // table name (snake_case is convention)
            joinColumns = @JoinColumn(name = "order_id"),       // FK to Order
            inverseJoinColumns = @JoinColumn(name = "product_id") // FK to Product
    )
    private List<Product> product = new ArrayList<>();


    private String status;


}


