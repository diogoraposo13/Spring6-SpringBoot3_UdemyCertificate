package net.diogoraposo.springboot.repository;

import net.diogoraposo.springboot.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
