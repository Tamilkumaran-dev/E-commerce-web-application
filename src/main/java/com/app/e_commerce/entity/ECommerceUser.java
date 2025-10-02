package com.app.e_commerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(name = "name", description = "Enter the name of the user", defaultValue = "Tamil")
    private String name;
    @Schema(name = "email", description = "Enter the user email id", defaultValue = "Tamil@gmail.com")
    private String email;
    @Schema(name = "password", description = "enter the password", defaultValue = "Tamil12345")
    private String password;

    @Schema(name = "role", description = "We can set the role, bydefault the role is user", defaultValue = "ROLE_USER")
    private String role = "ROLE_USER";

    @Schema(name = "personalDetail", description = "in this we have to sent the mobileNo and address",defaultValue = "{mobileNo : 7550322323,address :No 10, dubai kuruku sandu}")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "personal_detail_id")
    private UserPersonalDetail personalDetail;


    @Schema(name = "cart", description = "This we have have to pass a list of Product object",defaultValue = "Product object")
    @ManyToMany
    @JoinTable(
            name = "user_cart",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> cart = new ArrayList<>();


    @Schema(name = "orders", description = "This we have to transfer the cart to order",defaultValue = "List of orders")
    @OneToMany(mappedBy = "buyer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

}
