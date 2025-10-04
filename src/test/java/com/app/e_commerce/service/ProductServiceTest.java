package com.app.e_commerce.service;

import com.app.e_commerce.Dto.DoneResponce;
import com.app.e_commerce.entity.Product;
import com.app.e_commerce.exception.CustomException.CommonException;
import com.app.e_commerce.repository.EcommerceUserRepo;
import com.app.e_commerce.repository.ProductRepo;
import com.app.e_commerce.util.JwtUtil;
import org.hibernate.grammars.hql.HqlParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

import java.net.Proxy;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Prduct service test")
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {


    @Mock
    private ProductRepo productRepo;

    @Mock
    private EcommerceUserRepo userRepo;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    public ProductService productService;

    @DisplayName("Add product without exception")
    @Test
    public void addProduct(){
        Product product = new Product();
        product.setId(1L);
        product.setProductName("Asus");
        product.setDescription("asus tuf gaming laptop");
        product.setPrice(70000);
        product.setImage("http://image/");
        product.setQuantity(800);

        given(productRepo.save(product)).willReturn(product);

        DoneResponce responce = productService.addProduct(product);

        assertThat(responce.getMessage()).isEqualTo("Successfully product is add");

    }

    @DisplayName("Add product without some of the fields")
    @Test
    public void addProductWithoutSomeField(){
        Product product = new Product();
        product.setId(1L);
        product.setProductName("Asus");
        product.setQuantity(800);

        assertThrows(CommonException.class,()->productService.addProduct(product));

    }

    @DisplayName("Get all products")
    @Test
    public void getAllProduct(){

        Pageable pageable = PageRequest.of(1 - 1, 10, Sort.by("updatedTime").descending());

        Page<Product> productList = new Page<Product>() {
            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return 0;
            }

            @Override
            public <U> Page<U> map(Function<? super Product, ? extends U> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List<Product> getContent() {
                return List.of();
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<Product> iterator() {
                return null;
            }
        };

        given(productRepo.findAll(pageable)).willReturn(productList);

        Page<Product> response = productService.getAllProduct(1,10);

        assertThat(response).isEqualTo(productList);

    }

    @DisplayName("Get a specfic product")
    @Test
    public void getSpecificProduct(){

        Product product = new Product();
        product.setId(1L);
        product.setProductName("Asus");
        product.setDescription("asus tuf gaming laptop");
        product.setPrice(70000);
        product.setImage("http://image/");
        product.setQuantity(800);

        given(productRepo.findById(1L)).willReturn(Optional.of(product));


        assertThat(productService.getProduct(1L).getProduct()).isEqualTo(Optional.of(product));

    }


    @DisplayName("Exception in Get a specfic product")
    @Test
    public void ExceptionInGetSpecificProduct(){

        Optional<Product> product = Optional.empty();

        given(productRepo.findById(1L)).willReturn(product);

        assertThrows(CommonException.class,()->productService.getProduct(1L));
    }

    @DisplayName("Search product")
    @Test
    public void searchProduct(){

        Pageable pageable = PageRequest.of(1 - 1 ,10);
        Page<Product> productList = new Page<Product>() {
            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return 0;
            }

            @Override
            public <U> Page<U> map(Function<? super Product, ? extends U> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List<Product> getContent() {
                return List.of();
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<Product> iterator() {
                return null;
            }
        };

        given(productRepo.searchProducts("asus",pageable)).willReturn(productList);

        assertThat(productService.searchProduct("asus",1,10)).isEqualTo(productList);
    }

}