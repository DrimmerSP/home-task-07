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
    //    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Operation(description = "Получить список всех заказов.")
    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders().stream()
                .map(orderMapper::entityToDto)
                .toList();
    }

    @Operation(description = "Полузить описание заказа по ID.")
    @GetMapping("/{id}")
    public OrderDto getOrdersByID(@PathVariable Integer id) {
        return orderMapper.entityToDto(orderService.getOrdersByID(id));
    }

    @Operation(description = "Создать заказ.")
    @PostMapping
    public OrderDto createOrder(@RequestBody OrderDto body) {  // 2. Взять фильм в аренду/купить
        return orderMapper.entityToDto(orderService.createOrder(orderMapper.dtoToEntity(body)));
    }

    @Operation(description = "Обновить информацию о заказе.")
    @PutMapping("/{id}")
    public OrderDto updateOrder(@PathVariable Integer id, @RequestBody OrderDto body) {
        return orderMapper.entityToDto(orderService.updateOrder(id, orderMapper.dtoToEntity(body)));
    }

    @Operation(description = "Удалить заказ.")
    @DeleteMapping("/{id}")
    public void deleteOrderByID(@PathVariable Integer id){
        orderService.deleteOrderByID(id);
    }


}
