package ru.homework.hometask07.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.hometask07.dao.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
}
