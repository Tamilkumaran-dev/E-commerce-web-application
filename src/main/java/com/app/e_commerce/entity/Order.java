package com.app.e_commerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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


    @OneToMany(mappedBy = "ordered" , cascade = CascadeType.ALL)
    private List<Product> product;

    private String status;

}
