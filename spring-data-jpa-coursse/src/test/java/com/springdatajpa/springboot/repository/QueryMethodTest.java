package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class QueryMethodTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByNameMethod(){
        Product product = productRepository.findByName("product 2");
        System.out.println(product);
    }

    @Test
    void findByIdMethod(){
        Optional<Product> product = productRepository.findById(1L);
        System.out.println(product.get());
    }

    @Test
    void findByNameOrDescriptionMethod(){
        List<Product> products = productRepository.findByNameOrDescription("product 1","product 2 description");
        products.forEach((p) ->{
            System.out.println(p);
        });
    }

    @Test
    void findByNameAndDescriptionMethod(){
        List<Product> products = productRepository.findByNameAndDescription("product 1","product 1 description");
        products.forEach((p) ->{
            System.out.println(p);
        });
    }

    @Test
    void findDistinctByNameMethod(){
        Product product = productRepository.findDistinctByName("product 1");
        System.out.println(product);
    }

    @Test
    void findByPriceGreaterThanMethod(){
        List<Product> products = productRepository.findByPriceGreaterThan(new BigDecimal(100));
        products.forEach((p) ->{
            System.out.println(p);
        });
    }

    @Test
    void findByPriceLessThanMethod(){
        List<Product> products = productRepository.findByPriceLessThan(new BigDecimal(200));
        products.forEach((p) ->{
            System.out.println(p);
        });
    }

    @Test
    void findByNameContainingMethod(){
        List<Product> products = productRepository.findByNameContaining("pro");
        products.forEach((p) ->{
            System.out.println(p);
        });
    }

    @Test
    void findByNameLikeMethod(){
        List<Product> products = productRepository.findByNameLike("product 1");
        products.forEach((p) ->{
            System.out.println(p);
        });
    }

    @Test
    void findByPriceBetweenMethod(){
        List<Product> products = productRepository.findByPriceBetween(new BigDecimal(100),new BigDecimal(300));
        products.forEach((p) ->{
            System.out.println(p);
        });
    }

    @Test
    void findByDateCreatedBetweenMethod(){

        LocalDateTime startDate = LocalDateTime.of(2024,1,1,19,52,54);
        LocalDateTime endDate = LocalDateTime.of(2024,1,1,19,52,56);
        List<Product> products = productRepository.findByDateCreatedBetween(startDate,endDate);
        products.forEach((p) ->{
            System.out.println(p);
        });
    }

    @Test
    void findByNameInMethod(){
        List<Product> products = productRepository.findByNameIn(List.of("product 1", "product 2"));
        products.forEach((p) ->{
            System.out.println(p);
        });

    }

    @Test
    void findFirst2ByOrderByNameAscMethod(){
        List<Product> products = productRepository.findFirst2ByOrderByNameAsc();
        products.forEach((p) ->{
            System.out.println(p);
        });
    }

    @Test
    void findTop1ByOrderByPriceDescMethod(){
        List<Product> products = productRepository.findTop1ByOrderByPriceDesc();
        products.forEach((p) ->{
            System.out.println(p);
        });
    }
}
