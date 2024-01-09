package net.diogoraposo.springboot.dto;

import lombok.Getter;
import lombok.Setter;
import net.diogoraposo.springboot.entity.Order;
import net.diogoraposo.springboot.entity.Payment;

@Getter
@Setter
public class OrderRequest {
    private Order order;
    private Payment payment;
}
