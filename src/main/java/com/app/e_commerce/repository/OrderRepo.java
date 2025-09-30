package com.app.e_commerce.repository;


import com.app.e_commerce.entity.Order;
import com.app.e_commerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {


}
