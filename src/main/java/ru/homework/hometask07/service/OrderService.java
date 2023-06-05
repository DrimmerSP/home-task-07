package ru.homework.hometask07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.hometask07.dao.OrderRepository;
import ru.homework.hometask07.dao.entity.OrderEntity;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity getOrdersByID(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    public OrderEntity createOrder(OrderEntity body) {
        return orderRepository.save(body);
    }

    public OrderEntity updateOrder(Integer id, OrderEntity body) {
        body.setId(id);
        return orderRepository.save(body);
    }

    public void deleteOrderByID(Integer id) {
        orderRepository.deleteById(id);
    }
}
