package com.app.e_commerce.repository;

import com.app.e_commerce.entity.ECommerceUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EcommerceUserRepo extends JpaRepository<ECommerceUser,Long> {

    Optional<ECommerceUser> findByEmail(String email);

}
