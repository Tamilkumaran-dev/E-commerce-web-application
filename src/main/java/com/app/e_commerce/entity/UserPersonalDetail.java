package com.app.e_commerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPersonalDetail {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(name = "mobileNo", description = "Enter the mobile number", defaultValue = "1755011312")
    private Long mobileNo;

    @Schema(name = "address", description = "Enter the address", defaultValue = "no 10, dubai kuruku santhu, dubai")
    private String address;


    @OneToOne(mappedBy = "personalDetail")
    @JsonIgnore
    private ECommerceUser user;

}
