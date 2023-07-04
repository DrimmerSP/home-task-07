package ru.homework.hometask07.service;

import org.springframework.stereotype.Service;
import ru.homework.hometask07.controller.dto.OrderDto;
import ru.homework.hometask07.dao.GenericRepository;
import ru.homework.hometask07.dao.OrderRepository;
import ru.homework.hometask07.dao.entity.FilmEntity;
import ru.homework.hometask07.dao.entity.OrderEntity;
import ru.homework.hometask07.mapper.GenericMapper;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService extends GenericService<OrderEntity, OrderDto> {
    private final OrderRepository orderRepository;

    public OrderService(GenericRepository<OrderEntity> repository,
                        GenericMapper<OrderEntity, OrderDto> mapper,
                        OrderRepository orderRepository) {
        super(repository, mapper);
        this.orderRepository = orderRepository;
    }

    public List<FilmEntity> getFilmsInUse(Long userId) {
        return orderRepository.getFilmsInUseByUserId(userId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public OrderEntity rentFilm(OrderEntity orderEntity) {
        if (orderEntity.getIsPurchase()) {
            orderEntity.setRentFrom(null);
            orderEntity.setRentTo(null);
            orderEntity.setIsReturned(null);
            return orderRepository.save(orderEntity);
        }

        orderEntity.setIsPurchase(null);
        int DEFAULT_PERIOD = 14;
        LocalDate rentTo = orderEntity.getRentTo() != null ?
                orderEntity.getRentTo() :
                orderEntity.getRentFrom().plus(Duration.ofDays(DEFAULT_PERIOD));
        orderEntity.setRentTo(rentTo);
        return orderRepository.save(orderEntity);
    }
}
