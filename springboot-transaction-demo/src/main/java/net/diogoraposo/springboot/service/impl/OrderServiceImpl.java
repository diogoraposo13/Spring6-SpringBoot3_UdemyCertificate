package net.diogoraposo.springboot.service.impl;

import jakarta.transaction.Transactional;
import net.diogoraposo.springboot.dto.OrderRequest;
import net.diogoraposo.springboot.dto.OrderResponse;
import net.diogoraposo.springboot.entity.Order;
import net.diogoraposo.springboot.entity.Payment;
import net.diogoraposo.springboot.exception.PaymentException;
import net.diogoraposo.springboot.repository.OrderRepository;
import net.diogoraposo.springboot.repository.PaymentRepository;
import net.diogoraposo.springboot.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;

    public OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }


    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {

        Order order = orderRequest.getOrder();
        order.setStatus("INPROGRESS");
        order.setOrderTrackingNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

        Payment payment = orderRequest.getPayment();

        if(!payment.getType().equals("DEBIT")){
            throw new PaymentException("Payment card type do not support");
        }

        payment.setOrderId(order.getId());
        paymentRepository.save(payment);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderTrackingNumber(order.getOrderTrackingNumber());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("SUCCESS");
        return orderResponse;
    }
}
