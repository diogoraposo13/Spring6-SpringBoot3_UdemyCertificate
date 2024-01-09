package net.diogoraposo.springboot.service;

import net.diogoraposo.springboot.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    List<Product> searchProducts(String query);

    Product createProduct(Product product);
}
