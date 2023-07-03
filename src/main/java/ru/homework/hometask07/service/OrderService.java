package ru.homework.hometask07.service;

import org.springframework.stereotype.Service;
import ru.homework.hometask07.controller.dto.OrderDto;
import ru.homework.hometask07.dao.GenericRepository;
import ru.homework.hometask07.dao.OrderRepository;
import ru.homework.hometask07.dao.entity.FilmEntity;
import ru.homework.hometask07.dao.entity.OrderEntity;
import ru.homework.hometask07.mapper.GenericMapper;

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

//    public List<OrderEntity> listAll() {
//        return orderRepository.findAll();
//    }

//    public OrderEntity getOne(Long id) {
//        return orderRepository.findById(id).orElse(null);
//    }

//    public OrderEntity create(OrderEntity body) {
//        return orderRepository.save(body);
//    }

//    public OrderEntity update(Long id, OrderEntity body) {
//        body.setId(id);
//        return orderRepository.save(body);
//    }

//    public void delete(Long id) {
//        orderRepository.deleteById(id);
//    }

    public List<FilmEntity> getFilmsInUse(Long userId) {
        return orderRepository.getFilmsInUseByUserId(userId);
    }
}
