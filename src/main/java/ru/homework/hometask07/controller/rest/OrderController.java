package ru.homework.hometask07.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.homework.hometask07.controller.dto.OrderDto;
import ru.homework.hometask07.mapper.OrderMapper;
import ru.homework.hometask07.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "Заказ", description = "Заказ:")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Operation(description = "Получить список всех заказов.")
    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderMapper.toDtos(orderService.listAll());
    }

    @Operation(description = "Полузить описание заказа по ID.")
    @GetMapping("/{id}")
    public OrderDto getOrdersByID(@PathVariable Long id) {
        return orderMapper.toDto(orderService.getOne(id));
    }

    @Operation(description = "Создать заказ.")
    @PostMapping
    public OrderDto createOrder(@RequestBody OrderDto body) {  // 2. Взять фильм в аренду/купить
        return orderMapper.toDto(orderService.create(orderMapper.toEntity(body)));
    }

    @Operation(description = "Обновить информацию о заказе.")
    @PutMapping("/{id}")
    public OrderDto updateOrder(@PathVariable Long id, @RequestBody OrderDto body) {
        return orderMapper.toDto(orderService.update(id, orderMapper.toEntity(body)));
    }

    @Operation(description = "Удалить заказ.")
    @DeleteMapping("/{id}")
    public void deleteOrderByID(@PathVariable Long id) {
        orderService.delete(id);
    }


}
