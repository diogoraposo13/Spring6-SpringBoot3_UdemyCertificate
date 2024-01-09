package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveMethod(){
        //create product
        Product product = new Product();
        product.setName("product 1");
        product.setDescription("product 1 description");
        product.setSku("100ABC");
        product.setPrice(new BigDecimal(100));
        product.setActive(true);
        product.setImageUrl("product1.png");

        //save product
        Product savedObject = productRepository.save(product);
        //display product info
        System.out.println(savedObject.getId());
        System.out.println(savedObject.toString());

    }

    @Test
    void updateUsingSaveMethod(){
        //find or retrieve an entity by id
        Long id = 1L;
        Product product = productRepository.findById(id).get();
        //update entity information
        product.setName("updated product 1");
        product.setDescription("updated product 1 description");
        //save updated entity
        Product updatedProduct = productRepository.save(product);
    }

    @Test
    void findByIdMethod(){
        Long id = 1L;

        Product product = productRepository.findById(id).get();
        System.out.println(product);

    }

    @Test
    void saveAllMethod(){
        //create product
        Product product2 = new Product();
        product2.setName("product 2");
        product2.setDescription("product 2 description");
        product2.setSku("100ABCD");
        product2.setPrice(new BigDecimal(200));
        product2.setActive(true);
        product2.setImageUrl("product2.png");

        //create product
        Product product3 = new Product();
        product3.setName("product 3");
        product3.setDescription("product 3 description");
        product3.setSku("100ABCDE");
        product3.setPrice(new BigDecimal(300));
        product3.setActive(true);
        product3.setImageUrl("product3.png");

        productRepository.saveAll(List.of(product2,product3));
    }

    @Test
    void findAllMethod(){
        List<Product> products = productRepository.findAll();
        for (Product product: products){
            System.out.println(product);
        }
    }

    @Test
    void deleteByIdMethod(){
        Long id=1L;
        productRepository.deleteById(id);
    }

    @Test
    void deleteMethod(){
        //find an entity by id
        Long id= 2L;
        Product product = productRepository.findById(id).get();
        //delete(entity)
        productRepository.delete(product);
    }

    @Test
    void deleteAllMethod(){
        productRepository.deleteAll();
    }

    @Test
    void deleteAllMethod_v2(){
        Product product1 =  productRepository.findById(10L).get();
        Product product2 =  productRepository.findById(11L).get();
        Product product3 =  productRepository.findById(12L).get();
        List<Product> products = List.of(product1,product2,product3);
        productRepository.deleteAll(products);
    }

    @Test
    void countMethod(){
        System.out.println(productRepository.count());
    }

    @Test
    void existByIdMethod(){
        Long id  = 1L;

        boolean result = productRepository.existsById(id);
        System.out.println(result);
    }


}