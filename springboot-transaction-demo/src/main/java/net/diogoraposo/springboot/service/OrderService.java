package net.diogoraposo.springboot.service;

import net.diogoraposo.springboot.dto.OrderRequest;
import net.diogoraposo.springboot.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
}
