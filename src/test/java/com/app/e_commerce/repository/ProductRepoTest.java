package com.app.e_commerce.repository;

import com.app.e_commerce.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    @Test
    public void getProductBySearch(){

        //give
        Product product = new Product();
        product.setProductName("Asus TUF A15");
        product.setDescription("Gaming laptop a15 with GTX1650, intel i5 10gen, 16gb ram");
        product.setImage("https://tse1.mm.bing.net/th/id/OIP.KrlJ2pYTvp_YTE8od8QHJQAAAA?rs=1&pid=ImgDetMain&o=7&rm=3");
        product.setPrice(70000);
        product.setQuantity(100);

        Pageable pageable = PageRequest.of(0 ,10);

        productRepo.save(product);

        //when
        Page<Product> returnProduct = productRepo.searchProducts("asus",pageable);

        //then
        assertThat(returnProduct.getContent().size()).isGreaterThan(0);
        assertThat(returnProduct.getContent().get(0).getProductName()).isEqualTo("Asus TUF A15");
    }

}