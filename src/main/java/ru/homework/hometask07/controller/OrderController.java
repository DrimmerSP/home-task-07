package ru.homework.hometask07.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.homework.hometask07.dao.OrderRepository;
import ru.homework.hometask07.dao.entity.DirectorEntity;
import ru.homework.hometask07.dao.entity.OrderEntity;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "Заказ", description = "Заказ:")
@RequiredArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;

    @Operation(description = "Получить список всех заказов.")
    @GetMapping
    public List<OrderEntity> getAllOrders(){
        return orderRepository.findAll();
    }

    @Operation(description = "Полузить описание заказа по ID.")
    @GetMapping("/{id}")
    public OrderEntity getOrdersByID(@PathVariable Integer id){
        return orderRepository.findById(id).orElse(null);
    }

    @Operation(description = "Создать заказ.")
    @PostMapping
    public OrderEntity createOrder(@RequestBody OrderEntity body){
        return orderRepository.save(body);
    }

    @Operation(description = "Обновить информацию о заказе.")
    @PutMapping("/{id}")
    public OrderEntity updateOrder(@PathVariable Integer id, @RequestBody OrderEntity body){
        body.setId(id);
        return orderRepository.save(body);
    }

    @Operation(description = "Удалить заказ.")
    @DeleteMapping("/{id}")
    public void deleteOrderByID(@PathVariable Integer id){
        orderRepository.deleteById(id);
    }
}
