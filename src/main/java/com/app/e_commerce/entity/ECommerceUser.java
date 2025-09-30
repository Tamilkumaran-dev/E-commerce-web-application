package com.app.e_commerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ECommerceUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role = "ROLE_USER";

    @ManyToMany
    @JoinTable(
            name = "user_cart",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> cart = new ArrayList<>();

    @OneToMany(mappedBy = "buyer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

}
